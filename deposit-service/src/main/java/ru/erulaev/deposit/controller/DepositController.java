package ru.erulaev.deposit.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.erulaev.deposit.dto.DepositRequestDTO;
import ru.erulaev.deposit.dto.DepositResponseDTO;
import ru.erulaev.deposit.service.DepositService;

@RestController
@AllArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PostMapping("/")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO) {
        return depositService.deposit(depositRequestDTO.getAccountId(),
                depositRequestDTO.getBillId(), depositRequestDTO.getAmount());
    }
}
