package com.example.springhateoas.domains.order;

import com.example.springhateoas.domains.customer.CustomerResources;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
class FindOrderByCustomerId {

    FindOrderGateway findOrderGateway;

    CustomerResources customerResources;

    public List<Order> execute(int customerId) {
        try {
            return findOrderGateway.byCustomerId(customerId)
                    .stream()
                    .map(order -> Order.buildCustomer(order, customerResources.findCustomerName(customerId)))
                    .collect(toList());
        } catch (Exception e) {
            throw new OrderNotFoundException(e);
        }
    }
}
