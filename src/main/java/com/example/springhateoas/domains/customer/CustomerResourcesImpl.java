package com.example.springhateoas.domains.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CustomerResourcesImpl implements CustomerResources {

    FindCustomerById findCustomerById;

    @Override
    public String findCustomerName(int customerId) {
        return findCustomerById.execute(customerId).getName();
    }

}
