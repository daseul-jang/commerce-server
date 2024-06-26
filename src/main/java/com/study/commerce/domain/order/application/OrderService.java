package com.study.commerce.domain.order.application;

import com.study.commerce.domain.item.dao.ItemRepository;
import com.study.commerce.domain.item.entity.ItemEntity;
import com.study.commerce.domain.order.dao.OrderItemRepository;
import com.study.commerce.domain.order.dao.OrderProductRepository;
import com.study.commerce.domain.order.dao.OrderRepository;
import com.study.commerce.domain.order.dto.*;
import com.study.commerce.domain.order.entity.OrderEntity;
import com.study.commerce.domain.order.entity.OrderItemEntity;
import com.study.commerce.domain.product.dao.ProductRepository;
import com.study.commerce.domain.product.entity.ProductEntity;
import com.study.commerce.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderProductRepository orderProductRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        OrderEntity order = OrderEntity.createOrder(orderRequest);
        orderRepository.save(order);

        List<OrderSimpleItemResponse> orderSimpleItems = new ArrayList<>();

        orderRequest.getOrderItems().forEach(requestItem -> {
            ItemEntity findItem = itemRepository.findByIdAndDeletedAtIsNull(requestItem.getId())
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."));

            findItem.verifyTitle(requestItem.getTitle());
            findItem.verifyPrice(requestItem.getPrice());

            OrderItemEntity orderItem = findItem.createItem(order, requestItem.getQuantity());
            orderItemRepository.save(orderItem);

            requestItem.getOrderProducts().forEach(requestProduct -> {
                findItem.getProducts().forEach(itemProduct -> {
                    itemProduct.verifyQuantity(requestProduct.getQuantity());
                });

                ProductEntity findProduct = productRepository.findByIdAndDeletedAtIsNull(requestProduct.getId())
                        .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "제품이 존재하지 않습니다."));

                findProduct.verifyName(requestProduct.getName());
                findProduct.verifyStock(requestProduct.getQuantity() * requestItem.getQuantity());

                orderProductRepository.save(findProduct.createOrderProduct(orderItem, requestProduct.getQuantity()));

                findProduct.minusStock(requestProduct.getQuantity() * requestItem.getQuantity());
            });

            order.addTotalItemPrice(findItem.calculatePrice(requestItem.getQuantity()));

            orderSimpleItems.add(OrderSimpleItemResponse.of(orderItem));
        });

        return OrderResponse.of(order, orderSimpleItems);
    }
}
