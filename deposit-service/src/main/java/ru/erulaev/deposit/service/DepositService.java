package ru.erulaev.deposit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.erulaev.deposit.dto.DepositResponseDTO;
import ru.erulaev.deposit.entity.Deposit;
import ru.erulaev.deposit.exception.DepositServiceException;
import ru.erulaev.deposit.repository.DepositRepository;
import ru.erulaev.deposit.rest.AccountServiceClient;
import ru.erulaev.deposit.rest.BillServiceClient;
import ru.erulaev.deposit.rest.dto.AccountResponseDTO;
import ru.erulaev.deposit.rest.dto.BillRequestDTO;
import ru.erulaev.deposit.rest.dto.BillResponseDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DepositService {

    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;

    private final RabbitTemplate rabbitTemplate;

    public DepositResponseDTO deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account is null and bill is null");
        }

        if (billId != null) {
            BillResponseDTO billResponseDTO = billServiceClient.getBillById(billId);
            BillRequestDTO billRequestDTO = createBillRequestDTO(amount, billResponseDTO);

            billServiceClient.updateBill(billId, billRequestDTO);

            AccountResponseDTO accountResponseDTO = accountServiceClient.getAccountById(billResponseDTO.getAccountId());
            depositRepository.save(new Deposit(billId, amount, accountResponseDTO.getEmail(), OffsetDateTime.now()));

            return createResponse(amount, accountResponseDTO);
        }

        BillResponseDTO defaultBill = getDefaultBill(accountId);
        BillRequestDTO billRequestDTO = createBillRequestDTO(amount, defaultBill);
        billServiceClient.updateBill(defaultBill.getId(), billRequestDTO);
        AccountResponseDTO account = accountServiceClient.getAccountById(accountId);
        depositRepository.save(new Deposit(defaultBill.getId(), amount, account.getEmail(), OffsetDateTime.now()));

        return createResponse(amount, account);
    }

    private DepositResponseDTO createResponse(BigDecimal amount, AccountResponseDTO accountResponseDTO) {
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(amount, accountResponseDTO.getEmail());
        ObjectMapper mapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, mapper.writeValueAsString(depositResponseDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDTO;
    }

    private BillRequestDTO createBillRequestDTO(BigDecimal amount, BillResponseDTO billResponseDTO) {
        BillRequestDTO billRequestDTO = new BillRequestDTO();
        billRequestDTO.setAccountId(billResponseDTO.getAccountId());
        billRequestDTO.setCreationDate(billResponseDTO.getCreationDate());
        billRequestDTO.setIsDefault(billResponseDTO.getIsDefault());
        billRequestDTO.setOverdraftEnabled(billResponseDTO.isOverdraftEnabled());
        billRequestDTO.setAmount(billResponseDTO.getAmount().add(amount));
        return billRequestDTO;
    }

    private BillResponseDTO getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId).stream()
                .filter(BillResponseDTO::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositServiceException("Unable find default bill for account"));
    }
}
