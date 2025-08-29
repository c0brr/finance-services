package ru.erulaev.deposit.entity;

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
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long billId;

    private BigDecimal amount;

    private String email;

    private OffsetDateTime creationDate;

    public Deposit(Long billId, BigDecimal amount, String email, OffsetDateTime creationDate) {
        this.billId = billId;
        this.amount = amount;
        this.email = email;
        this.creationDate = creationDate;
    }
}
