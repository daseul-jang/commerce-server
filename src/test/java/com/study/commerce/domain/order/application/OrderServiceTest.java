package com.study.commerce.domain.order.application;

import com.study.commerce.domain.item.dao.ItemProductRepository;
import com.study.commerce.domain.item.dao.ItemRepository;
import com.study.commerce.domain.item.entity.ItemEntity;
import com.study.commerce.domain.item.entity.ItemProductEntity;
import com.study.commerce.domain.order.dto.OrderItemRequest;
import com.study.commerce.domain.order.dto.OrderProductRequest;
import com.study.commerce.domain.order.dto.OrderRequest;
import com.study.commerce.domain.product.dao.ProductRepository;
import com.study.commerce.domain.product.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemProductRepository itemProductRepository;

    private final String productName = "신라면";
    private final BigDecimal productPrice = BigDecimal.valueOf(2500L);
    private final Long productStock = 100L;

    private final String itemTitle = "신라면 5개입";
    private final String itemDescription = "대충 신라면 판다는 글";
    private final BigDecimal itemPrice = BigDecimal.valueOf(10000L);

    @BeforeEach
    void beforeEach() {
        ProductEntity product = productRepository.save(
                ProductEntity.builder()
                        .name(productName)
                        .price(productPrice)
                        .stock(productStock)
                        .build());

        ItemEntity item = itemRepository.save(
                ItemEntity.builder()
                        .title(itemTitle)
                        .description(itemDescription)
                        .price(itemPrice)
                        .build());

        ItemProductEntity itemProduct = itemProductRepository.save(
                ItemProductEntity.builder()
                        .product(product)
                        .item(item)
                        .quantity(5L)
                        .build()
        );

        itemProduct.addItem(item);
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    @DisplayName("동시성 테스트")
    public void 동시에_100번_주문() throws InterruptedException {
        int count = 5;
        AtomicInteger successCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                try {
                    orderService.save(getOrderRequest());
                    successCount.getAndIncrement();
                    System.out.println("주문 성공");
                } catch (Exception e) {
                    System.out.println("주문 실패 : " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        //assertThat(successCount.get()).isEqualTo(10);

        ProductEntity product = productRepository.findById(1L).get();
        assertThat(product.getStock()).isEqualTo(75L);
    }

    private OrderRequest getOrderRequest() {
        OrderProductRequest orderProductRequest = OrderProductRequest.builder()
                .id(1L)
                .name(productName)
                .price(productPrice)
                .quantity(5L)
                .build();

        OrderItemRequest orderItemRequest = OrderItemRequest.builder()
                .id(1L)
                .title(itemTitle)
                .price(itemPrice)
                .quantity(1L)
                .orderProducts(List.of(orderProductRequest))
                .build();

        return OrderRequest.builder()
                .ordererName("장다슬")
                .ordererPhone("01012345678")
                .receiverName("장다슬")
                .receiverPhone("01012345678")
                .receiverAddress("광주광역시 서구 상무대로 1번길")
                .receiverAddressDetail("상무빌라 502호")
                .receiverPostcode("11111")
                .deliveryRequest("문 앞 보관")
                .orderItems(List.of(orderItemRequest))
                .build();
    }
}
