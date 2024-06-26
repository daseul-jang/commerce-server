package com.study.commerce.domain.item.entity;

import com.study.commerce.domain.product.entity.ProductEntity;
import com.study.commerce.global.entity.BaseEntity;
import com.study.commerce.global.exception.BusinessException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_product")
public class ItemProductEntity extends BaseEntity {
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ProductEntity product;

    public void addItem(ItemEntity item) {
        this.item = item;
        item.getProducts().add(this);
    }

    public void verifyQuantity(Long requestQuantity) {
        if (!this.quantity.equals(requestQuantity)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "제품 수량이 일치하지 않습니다.");
        }
    }
}
