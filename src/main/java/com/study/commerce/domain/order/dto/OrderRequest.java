package com.study.commerce.domain.order.dto;

import com.study.commerce.global.exception.BusinessException;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequest {
    private String ordererName;
    private String ordererPhone;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String receiverAddressDetail;
    private String receiverPostcode;
    private String deliveryRequest;
    private List<OrderItemRequest> orderItems;
}
