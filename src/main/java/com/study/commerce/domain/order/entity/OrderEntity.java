package com.study.commerce.domain.order.entity;

import com.study.commerce.domain.order.dto.OrderRequest;
import com.study.commerce.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`order`")
public class OrderEntity extends BaseEntity {
    private String orderNo;

    private String ordererName;

    private String ordererPhone;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private String receiverAddressDetail;

    private String receiverPostcode;

    private String deliveryRequest;

    private BigDecimal totalItemPrice;

    private BigDecimal deliveryPrice;

    public static OrderEntity createOrder(OrderRequest request) {
        return OrderEntity.builder()
                .orderNo(generateOrderNo(request.getOrdererPhone()))
                .ordererName(request.getOrdererName())
                .ordererPhone(request.getOrdererPhone())
                .receiverName(request.getReceiverName())
                .receiverPhone(request.getReceiverPhone())
                .receiverAddress(request.getReceiverAddress())
                .receiverAddressDetail(request.getReceiverAddressDetail())
                .receiverPostcode(request.getReceiverPostcode())
                .deliveryRequest(request.getDeliveryRequest())
                .totalItemPrice(BigDecimal.ZERO)
                .deliveryPrice(BigDecimal.valueOf(3000))
                .build();
    }

    private static String generateOrderNo(String ordererPhone) {
        int index = ordererPhone.length() - 4;
        String endNumber = ordererPhone.substring(index);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        return timestamp + endNumber;
    }

    public void addTotalItemPrice(BigDecimal itemPrice) {
        this.totalItemPrice = this.totalItemPrice.add(itemPrice);
    }
}
