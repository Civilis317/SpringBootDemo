package org.boip.demo.rest.io;

import io.swagger.annotations.ApiModelProperty;
import org.boip.demo.rest.model.Order;

public class OrderRequest {
    @ApiModelProperty(notes = "The order object", position = 1)
    private Order order;

    // getters and setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
