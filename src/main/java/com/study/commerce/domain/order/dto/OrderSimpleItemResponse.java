package com.study.commerce.domain.order.dto;

import com.study.commerce.domain.order.entity.OrderItemEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderSimpleItemResponse {
    private Long id;
    private Long itemId;
    private String title;
    private BigDecimal price;
    private Long quantity;

    public static OrderSimpleItemResponse of(OrderItemEntity orderItem) {
        return OrderSimpleItemResponse.builder()
                .id(orderItem.getId())
                .itemId(orderItem.getItemId())
                .title(orderItem.getTitle())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
