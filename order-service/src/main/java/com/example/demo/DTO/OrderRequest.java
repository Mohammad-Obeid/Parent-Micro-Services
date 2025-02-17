package com.example.demo.DTO;

import com.example.demo.Model.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequest {
    private long id;
    private double totprice;
    private List<OrderItemDTO> orderItems;
}
