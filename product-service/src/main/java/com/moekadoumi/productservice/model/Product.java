package com.moekadoumi.productservice.src.main.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;


}
