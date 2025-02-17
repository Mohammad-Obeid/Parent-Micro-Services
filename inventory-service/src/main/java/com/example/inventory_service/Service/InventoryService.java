package com.example.inventory_service.Service;

import com.example.inventory_service.DTO.InventoryResponse;
import com.example.inventory_service.Repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                        .available(inventory.getQuantity()>0)
                        .skuCode(inventory.getSkuCode())
                        .build()).toList();
    }
}
