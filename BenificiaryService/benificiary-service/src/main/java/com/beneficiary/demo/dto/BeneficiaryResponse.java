package com.beneficiary.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeneficiaryResponse {

    private Long beneficiaryId;

    private Long customerId;

    private String beneficiaryName;

    private String accountNumber;

    private String bankName;

    private String ifscCode;
}