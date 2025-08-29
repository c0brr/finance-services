package ru.erulaev.deposit.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.erulaev.deposit.rest.dto.BillRequestDTO;
import ru.erulaev.deposit.rest.dto.BillResponseDTO;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.GET)
    BillResponseDTO getBillById(@PathVariable("billId") long billId);

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.PUT)
    void updateBill(@PathVariable("billId") long billId, BillRequestDTO billRequestDTO);

    @RequestMapping(value = "bills/account/{accountId}", method = RequestMethod.GET)
    List<BillResponseDTO> getBillsByAccountId(@PathVariable long accountId);
}
