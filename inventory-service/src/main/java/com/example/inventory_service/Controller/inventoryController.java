package com.example.inventory_service.Controller;

import com.example.inventory_service.DTO.InventoryResponse;
import com.example.inventory_service.Service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class inventoryController {
    private final InventoryService inventoryService;

    public inventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
