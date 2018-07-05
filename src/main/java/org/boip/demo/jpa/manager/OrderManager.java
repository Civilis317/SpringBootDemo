package org.boip.demo.jpa.manager;

import org.boip.demo.jpa.entity.OrderEntity;
import org.boip.demo.jpa.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class OrderManager {
    private final OrderRepository repository;

    public OrderManager(OrderRepository repository) {
        this.repository = repository;
    }

    public Stream<OrderEntity> getAllOrders() {
        return StreamSupport.stream(repository.findAll().spliterator(), true);
    }

    public Optional<OrderEntity> findById(Long id) {
        return repository.findById(id);
    }


}
