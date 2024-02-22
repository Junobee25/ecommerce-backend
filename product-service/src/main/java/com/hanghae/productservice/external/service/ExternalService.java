package com.hanghae.productservice.external.service;

import com.hanghae.productservice.domain.constant.ErrorCode;
import com.hanghae.productservice.domain.entity.Product;
import com.hanghae.productservice.domain.repository.ProductRepository;
import com.hanghae.productservice.exception.ProductServiceApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final ProductRepository productRepository;

    public Integer getProductPrice(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceApplicationException(ErrorCode.PRODUCT_NOT_FOUND));

        return product.getPrice();
    }
}
