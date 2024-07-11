package com.study.commerce.domain.product.dao;

import com.study.commerce.domain.product.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByIdAndDeletedAtIsNull(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id = :id and p.deletedAt is null")
    Optional<ProductEntity> findByIdAndDeletedAtIsNullForUpdate(@Param("id") Long id);
}
