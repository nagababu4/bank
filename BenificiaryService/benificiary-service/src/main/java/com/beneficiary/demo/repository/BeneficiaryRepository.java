package com.beneficiary.demo.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beneficiary.demo.entity.Beneficiary;


public interface BeneficiaryRepository
        extends JpaRepository<Beneficiary, Long> {

    List<Beneficiary> findByCustomerId(Long customerId);

    Optional<Beneficiary> findByCustomerIdAndAccountNumber(
            Long customerId,
            String accountNumber);

	boolean existsByAccountNumber(String accountNumber);
}