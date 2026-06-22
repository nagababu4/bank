package com.beneficiary.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BeneficiaryRequest {

    private Long customerId;

    @NotBlank
    private String beneficiaryName;

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String bankName;

    @NotBlank
    private String ifscCode;
}