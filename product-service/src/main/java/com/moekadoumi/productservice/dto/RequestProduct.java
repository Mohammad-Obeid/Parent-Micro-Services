package com.moekadoumi.productservice.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestProduct {
    private String name;
    private String description;
    private double price;
}
