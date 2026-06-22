package com.transaction.demo.feign;

import com.transaction.demo.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ACCOUNTSERVICE")
public interface AccountFeignClient {

    @GetMapping("/accounts/{accountNumber}")
    AccountResponse getAccount(
            @PathVariable String accountNumber);

    @PutMapping("/accounts/{accountNumber}")
    AccountResponse updateAccount(
            @PathVariable String accountNumber,
            @RequestBody AccountResponse account);
}