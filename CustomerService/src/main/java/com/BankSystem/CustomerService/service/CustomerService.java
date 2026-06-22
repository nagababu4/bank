package com.BankSystem.CustomerService.service;


import java.util.List;

import com.BankSystem.CustomerService.dto.CustomerRequest;
import com.BankSystem.CustomerService.dto.CustomerResponse;
import com.BankSystem.CustomerService.entity.Customer;
public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Long customerId);

    CustomerResponse updateCustomer(Long customerId, CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

   
}