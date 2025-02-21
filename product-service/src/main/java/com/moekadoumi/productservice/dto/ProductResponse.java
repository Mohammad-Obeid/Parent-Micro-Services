package com.moekadoumi.productservice.src.main.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
}
