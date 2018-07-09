package org.boip.demo;

import org.boip.demo.rest.io.OrderRequest;
import org.boip.demo.rest.io.OrderResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudTest extends AbstractBaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CrudTest.class);

    @LocalServerPort
    int randomServerPort;

    @Test
    public void getList() {
        ResponseEntity<OrderResponse> listResponse = getRestTemplate().exchange(assembleGetListUrl(randomServerPort), HttpMethod.GET, new HttpEntity<String>(getHeaders()), OrderResponse.class);
        boolean test = HttpStatus.OK.equals(listResponse.getStatusCode());
        Assert.isTrue(test, "get list failed");
        if (test) {
            logResponse("getList()", listResponse.getBody());
        }
    }

    @Test
    public void createOrder() {
        ResponseEntity<OrderResponse> createResponse = insertOrder();
        boolean test = HttpStatus.OK.equals(createResponse.getStatusCode());
        Assert.isTrue(test, "Create record failed");
        if (test) {
            logResponse("createOrder()", createResponse.getBody());
        }
    }


    @Test
    public void getOrderById() {
        ResponseEntity<OrderResponse> createResponse = insertOrder();
        Long orderId = createResponse.getBody().getOrders().get(0).getId();
        ResponseEntity<OrderResponse> getResponse = getRestTemplate().exchange(assembleGetUrl(randomServerPort) + orderId, HttpMethod.GET, new HttpEntity<String>(getHeaders()), OrderResponse.class);
        boolean test = HttpStatus.OK.equals(getResponse.getStatusCode());
        if (test) {
            logResponse("getOrderById()", getResponse.getBody());
        }
    }

    private ResponseEntity<OrderResponse> insertOrder() {
        HttpEntity<OrderRequest> createEntity = new HttpEntity<OrderRequest>(getOrderRequest(), getHeaders());
        return getRestTemplate().exchange(assembleCreateUrl(randomServerPort), HttpMethod.POST, createEntity, OrderResponse.class);
    }

    private void logResponse(String testName, OrderResponse response) {
        logger.info("Test: {}", testName);
        response.getOrders().stream().forEach(o -> {
            logger.info("Order: id={}, desc={}, emp={}, cust={}, details={}", o.getId(), o.getDescription(), o.getEmployee(), o.getCustomer(), o.getOrderDetails().size());
        });
    }
}
