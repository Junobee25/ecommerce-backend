package com.hanghae.stockhistoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockHistoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockHistoryServiceApplication.class, args);
    }

}
