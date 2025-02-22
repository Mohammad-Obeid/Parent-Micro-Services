package com.example.demo.Service;

import com.example.demo.Config.WebClientConfig;
import com.example.demo.DTO.InventoryResponse;
import com.example.demo.DTO.OrderItemDTO;
import com.example.demo.DTO.OrderRequest;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItems;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    public OrderService(OrderRepository orderRepository, WebClient.Builder webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    @Transactional
    public void CreateOrder(OrderRequest orderRequest) {
        // Create Order entity
        Order order = Order.builder()
                .totprice(0)
                .build();

        // Convert DTOs to Entity & Calculate total price
        List<OrderItems> orderItemsList = orderRequest.getOrderItems().stream()
                .map(dto -> OrderItems.builder()
                        .id(dto.getId())  // Save product ID
                        .skuCode(dto.getSkuCode())
                        .quantity(dto.getQuantity())
                        .price(dto.getPrice() * dto.getQuantity()) // Calculate item total price
                        .order(order)  // Explicitly link to parent Order
                        .build())
                .collect(Collectors.toList());




        // Calculate total price


        //let's call inventory service and check if the product is Available currently
        List<String> skuCodes = orderItemsList.stream().map(OrderItems::getSkuCode).toList();

        InventoryResponse[] res = webClient.build().get()
                .uri("http://localhost:8082/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        String [] skuCodesList = Arrays.stream(res)
                .map(e -> e.getSkuCode()).toList().toArray(new String[0]);

        for(String t : skuCodesList){
            System.out.println(t);
        }
        double totalPrice = orderItemsList.stream()
                .filter( or -> Arrays.stream(skuCodesList).toList().contains(or.getSkuCode()))
                .mapToDouble(OrderItems::getPrice)
                .sum();
        System.out.println(totalPrice);
        // Set calculated total price and order items
        order.setTotprice(totalPrice);
order.setOrderItems(orderItemsList);
        if(res.length==orderItemsList.size()) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is Not Available in Inventory");
        }
    }



    public void CreateOrder2(OrderRequest orderRequest) {
        Order order = Order.builder().build();
        System.out.println(orderRequest.getOrderItems().get(0));
        List<String> skuCodes = orderRequest.getOrderItems().stream()
                .map(OrderItemDTO::getSkuCode).toList();
        InventoryResponse [] inventoryResponse = webClient.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        List<OrderItems> ordItms = orderRequest.getOrderItems().stream()
                .filter(ord -> skuCodes.contains(ord.getSkuCode()))
                .map(orderItem -> OrderItems
                        .builder().quantity(orderItem.getQuantity())
                        .skuCode(orderItem.getSkuCode())
                        .price(orderItem.getPrice())
                        .order(order).build())
                .toList();
        if(inventoryResponse.length==ordItms.size()) {
            order.setOrderItems(ordItms);
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is Not Available in Inventory");
    }
    }

}
