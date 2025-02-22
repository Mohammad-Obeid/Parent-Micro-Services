package com.moekadoumi.productservice.Controller;

import com.moekadoumi.productservice.dto.ProductResponse;
import com.moekadoumi.productservice.dto.RequestProduct;
import com.moekadoumi.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse createProduct(@Valid @RequestBody RequestProduct requestProduct) {
        return productService.createProduct(requestProduct);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.returnProducts();
    }
}
