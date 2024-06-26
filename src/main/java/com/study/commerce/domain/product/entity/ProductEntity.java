package com.study.commerce.domain.product.entity;

import com.study.commerce.domain.order.entity.OrderItemEntity;
import com.study.commerce.domain.order.entity.OrderProductEntity;
import com.study.commerce.global.entity.BaseEntity;
import com.study.commerce.global.exception.BusinessException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    private String name;

    private BigDecimal price;

    private Long stock;

    public OrderProductEntity createOrderProduct(OrderItemEntity orderItem, Long quantity) {
        return OrderProductEntity.builder()
                .productId(this.getId())
                .name(this.name)
                .price(this.price)
                .quantity(quantity)
                .orderItem(orderItem)
                .build();
    }

    public void verifyName(String name) {
        if(!this.name.equals(name)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "제품 정보가 변경되었습니다.");
        }
    }

    public void verifyStock(Long quantity) {
        if(this.stock < quantity) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
        }
    }

    public void minusStock(Long quantity) {
        this.stock = this.stock - quantity;
    }
}
