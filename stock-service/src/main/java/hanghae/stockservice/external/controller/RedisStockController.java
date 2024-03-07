package hanghae.stockservice.external.controller;

import hanghae.stockservice.external.controller.dto.response.Response;
import hanghae.stockservice.external.service.RedisStockService;
import lombok.RequiredArgsConstructor;
import org.common.dto.StockFeignResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock-service")
@RequiredArgsConstructor
public class RedisStockController {

    private final RedisStockService redisStockService;

//    @GetMapping("/redis-stock")
//    public Response<Void> checkOrderQuantityAgainstProduct(@RequestParam Long productId, @RequestParam Integer orderQuantity) {
//        redisStockService.checkOrderQuantityAgainstProduct(productId, orderQuantity);
//        return Response.success();
//    }

    @PostMapping("/redis-stock/enroll-stock")
    public Response<Void> enrollStock(@RequestBody StockFeignResponse request) {
        redisStockService.enrollStock(request);
        return Response.success();
    }

    @PostMapping("/redis-stock/purchase")
    public Response<Void> purchase(@RequestBody StockFeignResponse request) {
        redisStockService.purchase(request);
        return Response.success();
    }

    @PostMapping("/redis-stock/cancel")
    public Response<Void> cancel(@RequestBody StockFeignResponse request) {
        redisStockService.cancel(request);
        return Response.success();
    }

    @GetMapping("/redis-stock/stock")
    public Response<Optional<Object>> findProductStock(@RequestParam("productId") Long productId) {
        return Response.success(redisStockService.findProductStock(productId));
    }
}
