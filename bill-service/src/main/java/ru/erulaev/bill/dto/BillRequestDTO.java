package ru.erulaev.bill.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
public class BillRequestDTO {

    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private boolean defaulted;

    private OffsetDateTime creationDate;

    private boolean overdraftEnabled;
}
