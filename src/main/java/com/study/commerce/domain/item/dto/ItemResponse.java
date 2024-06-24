package com.study.commerce.domain.item.dto;

import com.study.commerce.domain.item.entity.ItemEntity;
import lombok.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemResponse {
    private Long id;
    private String title;
    private BigDecimal price;

    public static ItemResponse of(ItemEntity item) {
        return ItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .price(item.getPrice())
                .build();
    }
}
