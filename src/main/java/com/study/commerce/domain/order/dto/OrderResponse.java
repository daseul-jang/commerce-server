package com.study.commerce.domain.order.dto;

import com.study.commerce.domain.order.entity.OrderEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponse {
    private Long id;
    private String orderNo;
    private BigDecimal totalItemPrice;
    private BigDecimal deliveryPrice;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private List<OrderSimpleItemResponse> orderItems;

    public static OrderResponse of(OrderEntity orderEntity, List<OrderSimpleItemResponse> orderItems) {
        BigDecimal totalItemPrice = orderEntity.getTotalItemPrice();
        BigDecimal deliveryPrice = orderEntity.getDeliveryPrice();

        return OrderResponse.builder()
                .id(orderEntity.getId())
                .orderNo(orderEntity.getOrderNo())
                .totalItemPrice(totalItemPrice)
                .deliveryPrice(deliveryPrice)
                .totalPrice(totalItemPrice.add(deliveryPrice))
                .createdAt(orderEntity.getCreatedAt())
                .orderItems(orderItems)
                .build();
    }
}
