package com.transaction.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BENEFICIARYSERVICE")
public interface BeneficiaryFeignClient {

    @GetMapping("/beneficiaries/validate/{accountNumber}")
    Boolean validateBeneficiary(
            @PathVariable String accountNumber);
}