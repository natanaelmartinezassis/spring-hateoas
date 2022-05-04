package com.example.springhateoas.domains.order;

import com.example.springhateoas.commons.ReadFile;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
class FindOrderFromJsonFile implements FindOrderGateway {

    ReadFile<Order> readFile;

    @Override
    public Optional<Order> byId(int orderId) throws IOException {
        return readFile.fromJson(new ClassPathResource("order-data.json").getFile(), Order.class)
                .stream()
                .filter(order -> order.getId() == orderId)
                .findFirst();
    }

    @Override
    public List<Order> byCustomerId(int customerId) throws IOException {
        return readFile.fromJson(new ClassPathResource("order-data.json").getFile(), Order.class)
                .stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(toList());
    }
}
