package com.study.commerce.domain.item.entity;

import com.study.commerce.domain.order.entity.OrderEntity;
import com.study.commerce.domain.order.entity.OrderItemEntity;
import com.study.commerce.global.entity.BaseEntity;
import com.study.commerce.global.exception.BusinessException;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
public class ItemEntity extends BaseEntity {
    private String title;

    private String description;

    private BigDecimal price;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<ItemProductEntity> products = new ArrayList<>();

    public OrderItemEntity createItem(OrderEntity order, Long quantity) {
        return OrderItemEntity.builder()
                .itemId(this.getId())
                .title(this.title)
                .price(this.price)
                .quantity(quantity)
                .order(order)
                .build();
    }

    public void verifyTitle(String title) {
        if (!this.title.equals(title)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "상품 정보가 변경되었습니다.");
        }
    }

    public void verifyPrice(BigDecimal price) {
        if (!this.price.equals(price)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "상품 가격이 일치하지 않습니다.");
        }
    }

    public BigDecimal calculatePrice(Long quantity) {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }
}
