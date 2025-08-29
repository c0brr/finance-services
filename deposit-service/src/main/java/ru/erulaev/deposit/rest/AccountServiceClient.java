package ru.erulaev.deposit.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.erulaev.deposit.rest.dto.AccountResponseDTO;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @RequestMapping(value = "accounts/{accountId}", method = RequestMethod.GET)
    AccountResponseDTO getAccountById(@PathVariable("accountId") long accountId);
}
