package com.example.springhateoas.domains.customer;

class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Exception e) {
        super("Customer Not Found");
    }

}
