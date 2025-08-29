package ru.erulaev.bill.repository;

import org.springframework.data.repository.CrudRepository;
import ru.erulaev.bill.entity.Bill;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {

    List<Bill> getBillsByAccountId(Long accountId);
}
