package com.hanghae.productservice.domain.repository;

import com.hanghae.productservice.domain.constant.ProductType;
import com.hanghae.productservice.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductType(ProductType productType);

}
