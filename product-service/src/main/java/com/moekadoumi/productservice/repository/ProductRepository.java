package com.moekadoumi.productservice.src.main.repository;

import com.example.youtCourse.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> { }
