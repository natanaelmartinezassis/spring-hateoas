package com.example.springhateoas.domains.order;

class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Exception e) {
        super("Order Not Found");
    }

}
