package org.boip.demo.rest.controller;

import org.boip.demo.rest.model.Order;
import org.boip.demo.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    private final StorageService storageService;

    public OrderController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value="/list")
    public List<Order> getOrderList() {
        return storageService.getAllOrders();
    }

}
