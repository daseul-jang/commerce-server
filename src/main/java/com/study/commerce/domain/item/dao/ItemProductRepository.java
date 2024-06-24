package com.study.commerce.domain.item.dao;

import com.study.commerce.domain.item.entity.ItemProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProductRepository extends JpaRepository<ItemProductEntity, Long> {
}
