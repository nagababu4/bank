package com.beneficiary.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.beneficiary.demo.dto.BeneficiaryRequest;
import com.beneficiary.demo.dto.BeneficiaryResponse;
import com.beneficiary.demo.service.BeneficiaryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeneficiaryResponse addBeneficiary(
            @Valid @RequestBody BeneficiaryRequest request) {

        return service.addBeneficiary(request);
    }

    @GetMapping("/customer/{id}")
    public List<BeneficiaryResponse> getBeneficiaries(
            @PathVariable Long id) {

        return service.getBeneficiaries(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBeneficiary(
            @PathVariable Long id) {

        service.deleteBeneficiary(id);

        return "Beneficiary deleted successfully";
    }

    @GetMapping("/validate/{accountNumber}")
    public boolean validateBeneficiary(
            @PathVariable String accountNumber) {

        return service.isBeneficiaryRegistered(accountNumber);
    }
}