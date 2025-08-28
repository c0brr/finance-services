package ru.erulaev.bill.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private boolean isDefault;

    private OffsetDateTime creationDate;

    private boolean overDraftEnabled;

    public Bill(long accountId, BigDecimal amount, boolean isDefault,
                OffsetDateTime creationDate, boolean overDraftEnabled) {
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.creationDate = creationDate;
        this.overDraftEnabled = overDraftEnabled;
    }

    public Bill(long accountId, BigDecimal amount, boolean overDraftEnabled, boolean isDefault) {
        this.overDraftEnabled = overDraftEnabled;
        this.isDefault = isDefault;
        this.amount = amount;
        this.accountId = accountId;
    }
}
