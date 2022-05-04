package com.example.springhateoas.domains.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
class Order extends RepresentationModel<Order> {

    private int id;
    private String product;
    private int quantity;
    private double price;
    private double amount;
    private int customerId;
    private String customer;

    public static Order buildCustomer(Order order, String customer) {
        order.setCustomer(customer);
        return order;
    }

    private void setCustomer(String customer) {
        this.customer = customer;
    }
}
