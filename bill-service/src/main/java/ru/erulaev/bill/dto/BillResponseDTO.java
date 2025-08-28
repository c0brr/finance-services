package ru.erulaev.bill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.erulaev.bill.entity.Bill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class BillResponseDTO {

    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private boolean isDefault;

    private OffsetDateTime creationDate;

    private boolean overDraftEnabled;

    public BillResponseDTO(Bill bill) {
        id = bill.getId();
        accountId = bill.getAccountId();
        amount = bill.getAmount();
        isDefault = bill.isDefault();
        creationDate = bill.getCreationDate();
        overDraftEnabled = bill.isOverDraftEnabled();
    }
}
