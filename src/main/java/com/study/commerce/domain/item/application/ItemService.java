package com.study.commerce.domain.item.application;

import com.study.commerce.domain.item.dao.ItemRepository;
import com.study.commerce.domain.item.dto.ItemDetailResponse;
import com.study.commerce.domain.item.dto.ItemResponse;
import com.study.commerce.domain.item.entity.ItemEntity;
import com.study.commerce.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemResponse> getItems() {
        return itemRepository.findAllByDeletedAtIsNull().stream().map(ItemResponse::of).toList();
    }

    public ItemDetailResponse getItem(Long id) {
        ItemEntity item = itemRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."));

        return ItemDetailResponse.of(item);
    }
}
