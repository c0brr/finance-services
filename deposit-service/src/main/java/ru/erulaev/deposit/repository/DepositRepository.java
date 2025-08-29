package ru.erulaev.deposit.repository;

import org.springframework.data.repository.CrudRepository;
import ru.erulaev.deposit.entity.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long> {

}
