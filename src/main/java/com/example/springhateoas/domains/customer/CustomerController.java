package com.example.springhateoas.domains.customer;

import com.example.springhateoas.domains.order.OrderController;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    FindCustomerById findCustomerById;

    @GetMapping(value = "/{customerId}", produces = {"application/hal+json"})
    public ResponseEntity<RepresentationModel<?>> getCustomerById(@PathVariable int customerId) {
        try {
            Customer customer = findCustomerById.execute(customerId);
            addCustomerAndOrdersLink(customer, customerId);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void addCustomerAndOrdersLink(Customer customer, int customerId) {
        customer.add(
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getOrdersByCustomer(customerId)).withSelfRel()
        );
    }

}
