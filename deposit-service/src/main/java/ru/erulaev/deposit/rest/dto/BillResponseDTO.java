package ru.erulaev.deposit.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillResponseDTO {

    private Long id;

    private Long accountId;

    private BigDecimal amount;

    private boolean isDefault;

    private OffsetDateTime creationDate;

    private boolean overdraftEnabled;

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
