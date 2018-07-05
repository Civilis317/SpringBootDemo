package org.boip.demo.storage;

import org.boip.demo.jpa.conversion.OrderConverter;
import org.boip.demo.jpa.entity.OrderEntity;
import org.boip.demo.jpa.manager.OrderManager;
import org.boip.demo.rest.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    private final OrderManager manager;


    public StorageService(OrderManager manager) {
        this.manager = manager;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>(1024);
        manager.getAllOrders().forEach(oe -> {
            orderList.add(OrderConverter.convertEntityToOrder(oe));
        });
        return orderList;
    }

    public Order getOrder(Long id) {
        OrderEntity entity = manager.findById(id).orElseThrow(() -> new EntityNotFoundException("Record not found for id: " + id));
        return OrderConverter.convertEntityToOrder(entity);
    }

    public Order save(Order order) {
        OrderEntity savedEntity = manager.save(OrderConverter.convertOrderToEntity(order));
        return OrderConverter.convertEntityToOrder(savedEntity);
    }
}
