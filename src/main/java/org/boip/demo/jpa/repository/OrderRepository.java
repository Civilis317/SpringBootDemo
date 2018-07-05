package org.boip.demo.jpa.repository;

import org.boip.demo.jpa.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
