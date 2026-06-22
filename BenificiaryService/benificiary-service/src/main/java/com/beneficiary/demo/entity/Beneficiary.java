package com.beneficiary.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beneficiaryId;

    private Long customerId;

    private String beneficiaryName;

    @Column(unique = true)
    private String accountNumber;

    private String bankName;

    private String ifscCode;
}