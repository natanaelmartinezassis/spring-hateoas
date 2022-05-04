package com.example.springhateoas.domains.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class FindCustomerById {

    FindCustomerGateway findCustomerGateway;

    public Customer execute(int customerId) {
        try {
            return findCustomerGateway.byId(customerId).orElseThrow();
        } catch (Exception e) {
            throw new CustomerNotFoundException(e);
        }
    }
}
