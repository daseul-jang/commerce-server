package com.study.commerce.domain.item.dao;

import com.study.commerce.domain.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findAllByDeletedAtIsNull();

    Optional<ItemEntity> findByIdAndDeletedAtIsNull(Long id);
}
