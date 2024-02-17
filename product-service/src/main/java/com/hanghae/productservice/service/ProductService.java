package com.hanghae.productservice.service;

import com.hanghae.productservice.controller.dto.ProductDetailDto;
import com.hanghae.productservice.controller.dto.ProductDto;
import com.hanghae.productservice.domain.constant.ErrorCode;
import com.hanghae.productservice.domain.constant.ProductType;
import com.hanghae.productservice.domain.entity.Product;
import com.hanghae.productservice.domain.repository.ProductRepository;
import com.hanghae.productservice.exception.ProductServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> viewProductList() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

    public List<ProductDto> viewProductType(ProductType productType) {
        List<Product> products = productRepository.findByProductType(productType);

        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

    public ProductDetailDto viewProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceApplicationException(ErrorCode.PRODUCT_NOT_FOUND));

        return ProductDetailDto.from(product);
    }

    public void enrollProduct(String name, Integer price, String description, ProductType productType) {
        productRepository.save(Product.of(name, price, description, productType));
    }
}
