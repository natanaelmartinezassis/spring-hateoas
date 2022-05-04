package com.example.springhateoas.domains.customer;

import java.io.IOException;
import java.util.Optional;

interface FindCustomerGateway {

    Optional<Customer> byId(int customerId) throws IOException;

}
