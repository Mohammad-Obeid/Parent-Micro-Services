package com.moekadoumi.productservice.src.main.Controller;

import com.example.youtCourse.dto.ProductResponse;
import com.example.youtCourse.dto.RequestProduct;
import com.example.youtCourse.service.ProductService;
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
    public void createProduct(@Valid @RequestBody RequestProduct requestProduct) {
        productService.createProduct(requestProduct);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.returnProducts();
    }
}
