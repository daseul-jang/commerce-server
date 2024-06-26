package com.study.commerce.domain.order.dto;

import com.study.commerce.domain.order.entity.OrderProductEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductResponse {
    private Long id;
    private Long productId;
    private String name;
    private BigDecimal price;
    private Long quantity;

    public static OrderProductResponse of(OrderProductEntity orderProductEntity) {
        return OrderProductResponse.builder()
                .id(orderProductEntity.getId())
                .productId(orderProductEntity.getProductId())
                .name(orderProductEntity.getName())
                .price(orderProductEntity.getPrice())
                .quantity(orderProductEntity.getQuantity())
                .build();
    }
}
