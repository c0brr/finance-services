package ru.erulaev.deposit.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class BillRequestDTO {

    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private boolean isDefault;

    private OffsetDateTime creationDate;

    private boolean overdraftEnabled;

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
