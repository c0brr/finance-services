package ru.erulaev.account.repository;

import org.springframework.data.repository.CrudRepository;
import ru.erulaev.account.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {


}
