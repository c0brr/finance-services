package ru.erulaev.deposit.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private OffsetDateTime creationDate;

    private List<Long> bills;
}
