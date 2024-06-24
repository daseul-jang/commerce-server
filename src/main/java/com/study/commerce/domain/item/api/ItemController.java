package com.study.commerce.domain.item.api;

import com.study.commerce.domain.item.application.ItemService;
import com.study.commerce.domain.item.dto.ItemDetailResponse;
import com.study.commerce.domain.item.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDetailResponse> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }
}
