package org.boip.demo.rest.controller;

import org.boip.demo.rest.io.OrderRequest;
import org.boip.demo.rest.io.OrderResponse;
import org.boip.demo.rest.model.Order;
import org.boip.demo.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    private final StorageService storageService;

    public OrderController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/list")
    public @ResponseBody
    OrderResponse getOrderList() {
        OrderResponse response = new OrderResponse();
        response.setOrders(storageService.getAllOrders());
        return response;
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody
    OrderResponse getOrder(@PathVariable(name = "id") Long id) {
        // todo: validate id
        OrderResponse response = new OrderResponse();
        response.getOrders().add(storageService.getOrder(id));
        return response;
    }

    @PostMapping(value = "/create")
    public @ResponseBody OrderResponse createOrder(@RequestBody OrderRequest request) {
        // todo: validate request, order not null etc
        request.getOrder().setDate(new Date());
        OrderResponse response = new OrderResponse();
        response.getOrders().add(storageService.save(request.getOrder()));
        return response;
    }

}
