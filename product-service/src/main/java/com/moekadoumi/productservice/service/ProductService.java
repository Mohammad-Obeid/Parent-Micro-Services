package com.moekadoumi.productservice.service;


import com.moekadoumi.productservice.dto.ProductResponse;
import com.moekadoumi.productservice.dto.RequestProduct;
import com.moekadoumi.productservice.model.Product;
import com.moekadoumi.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product mapToProduct(RequestProduct reqproduct) {
        return Product.builder().
                name(reqproduct.getName()).
                description(reqproduct.getDescription()).
                price(reqproduct.getPrice()).build();
    }
    public ProductResponse createProduct(RequestProduct product) {
        Product newProduct = mapToProduct(product);
        productRepository.save(newProduct);
        return ProductResponse.builder()
                .id(newProduct.getId())
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .description(newProduct.getDescription())
                .build();
    }

    public List<ProductResponse> returnProducts() {
//        List<Product> products = productRepository.findAll();
//        List<ProductResponse> requestProducts = new ArrayList<>();
//        for (Product product : products) {
//            requestProducts.add(
//                    ProductResponse.builder().name(product.getName()).description(product.getDescription()).price(product.getPrice()).build()
//            );
//        }
//        return requestProducts;

    List<ProductResponse> requestProducts = productRepository.findAll().stream()
            .map(product -> ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build())
            .collect(Collectors.toList());
    return requestProducts;

    //can be written in both ways
    }
}
