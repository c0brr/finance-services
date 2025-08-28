package ru.erulaev.bill.repository;

import org.springframework.data.repository.CrudRepository;
import ru.erulaev.bill.entity.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
