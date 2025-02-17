package com.example.inventory_service;

import com.example.inventory_service.Model.Inventory;
import com.example.inventory_service.Repository.InventoryRepository;
import com.example.inventory_service.Service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepo) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone-11");
			inventory.setQuantity(20);
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone-12");
			inventory1.setQuantity(10);
			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);
		};
	}
}
