package com.study.commerce.domain.item.dto;

import com.study.commerce.domain.item.entity.ItemProductEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    private Long stock;

    public static ItemProductResponse of(ItemProductEntity itemProduct) {
        return ItemProductResponse.builder()
                .id(itemProduct.getProduct().getId())
                .name(itemProduct.getProduct().getName())
                .price(itemProduct.getProduct().getPrice())
                .quantity(itemProduct.getQuantity())
                .stock(itemProduct.getProduct().getStock())
                .build();
    }
}
