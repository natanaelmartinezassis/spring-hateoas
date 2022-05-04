package com.example.springhateoas.domains.order;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

interface FindOrderGateway {

    Optional<Order> byId(int orderId) throws IOException;

    List<Order> byCustomerId(int customerId) throws IOException;
}
