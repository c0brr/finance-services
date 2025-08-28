package ru.erulaev.bill.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erulaev.bill.entity.Bill;
import ru.erulaev.bill.exception.NotFoundException;
import ru.erulaev.bill.repository.BillRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class BIllService {

    private final BillRepository billRepository;

    public Bill get(@PathVariable long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unable to find bill with id: " + id));
    }

    public Long create(long accountId, BigDecimal amount, boolean defaulted, boolean overdraftEnabled) {
        Bill bill = new Bill(accountId, amount, defaulted, OffsetDateTime.now(), overdraftEnabled);
        return billRepository.save(bill).getId();
    }

    public Bill update(long id, long accountId, BigDecimal amount, boolean defaulted, boolean overdraftEnabled) {
        Bill bill = new Bill(accountId, amount, defaulted, OffsetDateTime.now(), overdraftEnabled);
        bill.setId(id);
        return billRepository.save(bill);
    }

    public Bill delete(long id) {
        Bill deletedBill = get(id);
        billRepository.deleteById(id);
        return deletedBill;
    }
}
