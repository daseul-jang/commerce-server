package com.study.commerce.global.init;

import com.study.commerce.domain.item.dao.ItemProductRepository;
import com.study.commerce.domain.item.dao.ItemRepository;
import com.study.commerce.domain.item.entity.ItemEntity;
import com.study.commerce.domain.item.entity.ItemProductEntity;
import com.study.commerce.domain.product.dao.ProductRepository;
import com.study.commerce.domain.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Profile("!prod & !test")
@Component
@RequiredArgsConstructor
public class NotProd implements ApplicationRunner {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ItemProductRepository itemProductRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!itemRepository.findAll().isEmpty()) {
            return;
        }

        initProduct();
        initItem();
        initItemProduct();
    }

    private void initProduct() {
        productRepository.save(ProductEntity.builder().name("신라면").price(BigDecimal.valueOf(2500)).stock(100L).build());
        productRepository.save(ProductEntity.builder().name("삼다수").price(BigDecimal.valueOf(6000)).stock(2L).build());
        productRepository.save(ProductEntity.builder().name("포카칩").price(BigDecimal.valueOf(1500)).stock(0L).build());
        productRepository.save(ProductEntity.builder().name("빼빼로").price(BigDecimal.valueOf(1000)).stock(100L).build());
        productRepository.save(ProductEntity.builder().name("스윙칩").price(BigDecimal.valueOf(1500)).stock(100L).build());
    }

    private void initItem() {
        itemRepository.save(ItemEntity.builder().title("신라면 5개입").description("대충 신라면 판다는 글").price(BigDecimal.valueOf(10000)).build());
        itemRepository.save(ItemEntity.builder().title("삼다수 6개입").description("제주 삼다수 어쩌구").price(BigDecimal.valueOf(30000)).build());
        itemRepository.save(ItemEntity.builder().title("과자 세트").description("과자계의 스테디셀러").price(BigDecimal.valueOf(15000)).build());
    }

    private void initItemProduct() {
        itemProductRepository.save(ItemProductEntity.builder().item(itemRepository.findById(1L).get()).product(productRepository.findById(1L).get()).quantity(5L).build());
        itemProductRepository.save(ItemProductEntity.builder().item(itemRepository.findById(2L).get()).product(productRepository.findById(2L).get()).quantity(6L).build());

        ItemEntity item = itemRepository.findById(3L).get();
        itemProductRepository.save(ItemProductEntity.builder().item(item).product(productRepository.findById(3L).get()).quantity(5L).build());
        itemProductRepository.save(ItemProductEntity.builder().item(item).product(productRepository.findById(4L).get()).quantity(5L).build());
        itemProductRepository.save(ItemProductEntity.builder().item(item).product(productRepository.findById(5L).get()).quantity(5L).build());
    }
}
