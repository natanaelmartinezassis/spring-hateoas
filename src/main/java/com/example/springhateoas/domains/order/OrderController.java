package com.example.springhateoas.domains.order;

import com.example.springhateoas.domains.customer.CustomerController;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    FindOrderById findOrderById;
    FindOrderByCustomerId findOrderByCustomerId;

    @GetMapping(value = "/{orderId}", produces = {"application/hal+json"})
    public ResponseEntity<RepresentationModel<?>> getOrderById(@PathVariable int orderId) {
        try {
            Order order = findOrderById.execute(orderId);
            addOrderAndCustomerLink(order, order.getCustomerId());
            return ResponseEntity.ok(CollectionModel.of(order));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void addOrderAndCustomerLink(Order order, int customerId) {
        order.add(
                linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getCustomerById(customerId)).withSelfRel()
        );
    }

    @GetMapping(value = "/customer/{customerId}", produces = {"application/hal+json"})
    public ResponseEntity<CollectionModel<?>> getOrdersByCustomer(@PathVariable int customerId) {
        try {
            List<Order> orders = findOrderByCustomerId.execute(customerId);
            orders.forEach(order -> addOrderAndCustomerLink(order, customerId));
            return ResponseEntity.ok(CollectionModel.of(orders, addOrdersByCustomerLink(customerId)));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Link addOrdersByCustomerLink(int customerId) {
        return linkTo(methodOn(OrderController.class).getOrdersByCustomer(customerId)).withSelfRel();
    }

}
