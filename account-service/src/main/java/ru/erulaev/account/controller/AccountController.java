package ru.erulaev.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.erulaev.account.dto.AccountRequestDTO;
import ru.erulaev.account.dto.AccountResponseDTO;
import ru.erulaev.account.service.AccountService;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountResponseDTO get(@PathVariable long id) {
        return new AccountResponseDTO(accountService.get(id));
    }

    @PostMapping("/")
    public Long create(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.create(accountRequestDTO.getName(),
                accountRequestDTO.getEmail(),
                accountRequestDTO.getPhone(),
                accountRequestDTO.getBills());
    }

    @PutMapping("/{id}")
    public AccountResponseDTO update(@PathVariable long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        return new AccountResponseDTO(accountService.update(id, accountRequestDTO.getName(),
                accountRequestDTO.getEmail(),
                accountRequestDTO.getPhone(),
                accountRequestDTO.getBills()));
    }

    @DeleteMapping("/{id}")
    public AccountResponseDTO delete(@PathVariable long id) {
        return new AccountResponseDTO(accountService.delete(id));
    }
}
