package com.example.springhateoas.domains.customer;

import com.example.springhateoas.commons.ReadFile;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
class FindCustomerFromJsonFile implements FindCustomerGateway {

    ReadFile<Customer> readFile;

    @Override
    public Optional<Customer> byId(int customerId) throws IOException {
        return readFile.fromJson(new ClassPathResource("customer-data.json").getFile(), Customer.class)
                .stream()
                .filter(customer -> customer.getId() == customerId)
                .findFirst();
    }
}
