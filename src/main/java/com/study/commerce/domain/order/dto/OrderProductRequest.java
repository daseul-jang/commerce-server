package com.study.commerce.domain.order.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductRequest {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
}
