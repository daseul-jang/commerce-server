package com.study.commerce.domain.order.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemRequest {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private List<OrderProductRequest> orderProducts;
}
