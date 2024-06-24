package com.study.commerce.domain.item.dto;

import com.study.commerce.domain.item.entity.ItemEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDetailResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<ItemProductResponse> products;

    public static ItemDetailResponse of(ItemEntity item) {
        List<ItemProductResponse> productResponses =
                item.getProducts().stream().map(ItemProductResponse::of).toList();

        return ItemDetailResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .price(item.getPrice())
                .products(productResponses)
                .build();
    }
}
