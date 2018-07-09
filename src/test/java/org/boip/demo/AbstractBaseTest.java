package org.boip.demo;

import org.boip.demo.rest.io.OrderRequest;
import org.boip.demo.rest.model.Order;
import org.boip.demo.rest.model.OrderDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AbstractBaseTest {
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String CONTEXT_PATH = "/demo";
    private static final String GET_LIST_PATH = "/api/order/list";
    private static final String GET_PATH = "/api/order/get/";
    private static final String CREATE_PATH = "/api/order/create";

    protected RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected String assembleGetListUrl(int port) {
        return assembleUrl(port, GET_LIST_PATH);
    }

    protected String assembleGetUrl(int port) {
        return assembleUrl(port, GET_PATH);
    }

    protected String assembleCreateUrl(int port) {
        return assembleUrl(port, CREATE_PATH);
    }

    protected OrderRequest getOrderRequest() {
        OrderRequest request = new OrderRequest();
        Order order = new Order();
        order.setDescription("New order");
        order.setEmployee("John Doe");
        order.setCustomer("Elvis");
        OrderDetail detail1 = new OrderDetail();
        detail1.setProductCode("X-15");
        detail1.setDescription("A supersonic rocket airplane");
        detail1.setPrice(16.79);
        detail1.setAmount(Long.valueOf("1"));

        OrderDetail detail2 = new OrderDetail();
        detail2.setProductCode("SP-101");
        detail2.setDescription("A beginners spanish language course");
        detail2.setPrice(59.80);
        detail2.setAmount(Long.valueOf("1"));

        OrderDetail detail3 = new OrderDetail();
        detail3.setProductCode("G-001");
        detail3.setDescription("An autonomous driving car from Google");
        detail3.setPrice(16599.00);
        detail3.setAmount(Long.valueOf("2"));

        order.getOrderDetails().add(detail1);
        order.getOrderDetails().add(detail2);
        order.getOrderDetails().add(detail3);
        request.setOrder(order);

        return request;
    }

    private String assembleUrl(int port, String subPath) {
        StringBuilder sb = new StringBuilder(10);
        sb.append(PROTOCOL)
                .append(HOST)
                .append(":")
                .append(port)
                .append(CONTEXT_PATH)
                .append(subPath);
        return sb.toString();
    }
}
