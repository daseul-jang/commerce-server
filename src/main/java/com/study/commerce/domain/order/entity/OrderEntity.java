package com.study.commerce.domain.order.entity;

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

    private BigDecimal totalPrice;

    private BigDecimal deliveryPrice;

    private static String generateOrderNo(String ordererPhone) {
        int index = ordererPhone.length() - 4;
        String endNumber = ordererPhone.substring(index);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        // 현재 시간을 기반으로 한 타임스탬프 생성
        String timestamp = dateFormat.format(new Date());

        // 타임스탬프와 랜덤 숫자 결합
        return timestamp + endNumber;
    }
}
