package org.boip.demo.rest.io;

import org.boip.demo.rest.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private List<Order> orders = new ArrayList<>(1024);

    // getters and setters
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
