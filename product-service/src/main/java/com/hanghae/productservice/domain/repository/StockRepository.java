package com.hanghae.productservice.domain.repository;

import com.hanghae.productservice.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
