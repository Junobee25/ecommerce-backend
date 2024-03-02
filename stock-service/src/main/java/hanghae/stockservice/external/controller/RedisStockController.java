package hanghae.stockservice.external.controller;

import hanghae.stockservice.domain.entity.RedisStock;
import hanghae.stockservice.external.controller.dto.StockAdapterDto;
import hanghae.stockservice.external.controller.dto.response.Response;
import hanghae.stockservice.external.service.RedisStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock-service")
@RequiredArgsConstructor
public class RedisStockController {

    private final RedisStockService redisStockService;

    @PostMapping("/redis/enroll-stock")
    public Response<Void> enrollStock(@RequestBody StockAdapterDto request) {
        redisStockService.enrollStock(request);
        return Response.success();
    }

    @PostMapping("/redis/purchase")
    public Response<Void> purchase(@RequestBody StockAdapterDto request) {
        redisStockService.purchase(request);
        return Response.success();
    }

    @PostMapping("/redis/cancel")
    public Response<Void> cancel(@RequestBody StockAdapterDto request) {
        redisStockService.cancel(request);
        return Response.success();
    }

    @GetMapping("/redis/stock")
    public Response<Optional<RedisStock>> findProductStock(@RequestParam("productId") Long productId) {
        return Response.success(redisStockService.findProductStock(productId));
    }
}
