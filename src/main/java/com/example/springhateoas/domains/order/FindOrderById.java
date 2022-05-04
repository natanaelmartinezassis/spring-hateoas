package com.example.springhateoas.domains.order;

import com.example.springhateoas.domains.customer.CustomerResources;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class FindOrderById {

    FindOrderGateway findOrderGateway;

    CustomerResources customerResources;

    public Order execute(int orderId) {
        try {
            return findOrderGateway.byId(orderId)
                    .map(order -> Order.buildCustomer(order, customerResources.findCustomerName(order.getCustomerId())))
                    .orElseThrow();
        } catch (Exception e) {
            throw new OrderNotFoundException(e);
        }
    }
}
