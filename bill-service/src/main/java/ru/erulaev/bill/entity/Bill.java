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

    private boolean defaulted;

    private OffsetDateTime creationDate;

    private boolean overdraftEnabled;

    public Bill(long accountId, BigDecimal amount, boolean defaulted,
                OffsetDateTime creationDate, boolean overdraftEnabled) {
        this.accountId = accountId;
        this.amount = amount;
        this.defaulted = defaulted;
        this.creationDate = creationDate;
        this.overdraftEnabled = overdraftEnabled;
    }

    public Bill(long accountId, BigDecimal amount, boolean overdraftEnabled, boolean defaulted) {
        this.overdraftEnabled = overdraftEnabled;
        this.defaulted = defaulted;
        this.amount = amount;
        this.accountId = accountId;
    }
}
