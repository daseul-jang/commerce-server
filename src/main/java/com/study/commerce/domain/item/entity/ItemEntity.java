package com.study.commerce.domain.item.entity;

import com.study.commerce.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
