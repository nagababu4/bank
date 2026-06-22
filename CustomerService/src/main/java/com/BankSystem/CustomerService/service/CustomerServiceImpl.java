package com.BankSystem.CustomerService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.BankSystem.CustomerService.dto.CustomerRequest;
import com.BankSystem.CustomerService.dto.CustomerResponse;
import com.BankSystem.CustomerService.entity.Customer;
import com.BankSystem.CustomerService.exception.CustomerNotFoundException;
import com.BankSystem.CustomerService.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .address(request.getAddress())
                .panNumber(request.getPanNumber())
                .build();

        return mapToResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {

        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));

        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId,
                                           CustomerRequest request) {

        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setMobile(request.getMobile());
        customer.setAddress(request.getAddress());
        customer.setPanNumber(request.getPanNumber());

        return mapToResponse(repository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

  
    private CustomerResponse mapToResponse(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .address(customer.getAddress())
                .panNumber(customer.getPanNumber())
                .build();
    }
}