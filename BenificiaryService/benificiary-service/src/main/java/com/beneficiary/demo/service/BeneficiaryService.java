package com.beneficiary.demo.service;

import java.util.List;

import com.beneficiary.demo.dto.BeneficiaryRequest;
import com.beneficiary.demo.dto.BeneficiaryResponse;
public interface BeneficiaryService {

    BeneficiaryResponse addBeneficiary(
            BeneficiaryRequest request);

    List<BeneficiaryResponse> getBeneficiaries(
            Long customerId);

    void deleteBeneficiary(Long id);

    boolean isBeneficiaryRegistered(
            Long customerId,
            String accountNumber);

    boolean isBeneficiaryRegistered(
            String accountNumber);
}