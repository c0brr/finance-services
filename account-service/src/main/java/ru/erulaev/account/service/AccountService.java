package ru.erulaev.account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.erulaev.account.entity.Account;
import ru.erulaev.account.exception.NotFoundException;
import ru.erulaev.account.repository.AccountRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getAccount(long id) {
       return accountRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Unable to find account with id" + id));
    }

    public Long create(String name, String email, String phone, List<Long> bills) {
        Account account = new Account(name, email, phone, OffsetDateTime.now(), bills);
        return accountRepository.save(account).getId();
    }

    public Account update(Long id, String name, String email, String phone, List<Long> bills) {
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setBills(bills);
        return accountRepository.save(account);
    }

    public Account delete(long id) {
        Account deletedAccount = getAccount(id);
        accountRepository.deleteById(id);
        return deletedAccount;
    }
}
