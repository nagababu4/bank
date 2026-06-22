package com.beneficiary.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beneficiary.demo.dto.BeneficiaryRequest;
import com.beneficiary.demo.dto.BeneficiaryResponse;
import com.beneficiary.demo.entity.Beneficiary;
import com.beneficiary.demo.exception.BeneficiaryNotFoundException;
import com.beneficiary.demo.repository.BeneficiaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository repository;

    @Override
    public BeneficiaryResponse addBeneficiary(
            BeneficiaryRequest request) {

        Beneficiary beneficiary = Beneficiary.builder()
                .customerId(request.getCustomerId())
                .beneficiaryName(request.getBeneficiaryName())
                .accountNumber(request.getAccountNumber())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .build();

        return map(repository.save(beneficiary));
    }

    @Override
    public List<BeneficiaryResponse> getBeneficiaries(
            Long customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void deleteBeneficiary(Long id) {

        Beneficiary beneficiary = repository.findById(id)
                .orElseThrow(() ->
                        new BeneficiaryNotFoundException(
                                "Beneficiary not found"));

        repository.delete(beneficiary);
    }

    @Override
    public boolean isBeneficiaryRegistered(
            Long customerId,
            String accountNumber) {

        return repository
                .findByCustomerIdAndAccountNumber(
                        customerId,
                        accountNumber)
                .isPresent();
    }

    @Override
    public boolean isBeneficiaryRegistered(
            String accountNumber) {

        return repository
                .existsByAccountNumber(accountNumber);
    }

    private BeneficiaryResponse map(
            Beneficiary beneficiary) {

        return BeneficiaryResponse.builder()
                .beneficiaryId(beneficiary.getBeneficiaryId())
                .customerId(beneficiary.getCustomerId())
                .beneficiaryName(beneficiary.getBeneficiaryName())
                .accountNumber(beneficiary.getAccountNumber())
                .bankName(beneficiary.getBankName())
                .ifscCode(beneficiary.getIfscCode())
                .build();
    }
}