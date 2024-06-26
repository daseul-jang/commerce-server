package com.study.commerce.domain.order.dao;

import com.study.commerce.domain.order.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}
