package hanghae.stockservice.external.controller;

import hanghae.stockservice.external.controller.dto.response.Response;
import hanghae.stockservice.external.service.StockLockFacade;
import hanghae.stockservice.external.service.StockService;
import lombok.RequiredArgsConstructor;
import org.common.dto.StockFeignResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-service")
public class StockController {

    private final StockService stockService;
    private final StockLockFacade stockLockFacade;

    @GetMapping("/stock")
    public Response<Void> checkOrderQuantityAgainstProduct(@RequestParam Long productId, @RequestParam Integer orderQuantity) {
        stockService.checkOrderQuantityAgainstProduct(productId, orderQuantity);
        return Response.success();
    }
    @PostMapping("/stock/enroll-stock")
    public Response<Void> enrollStock(@RequestBody StockFeignResponse request) {
        stockService.enrollStock(request);
        return Response.success();
    }

    @PostMapping("/stock/purchase")
    public Response<Void> purchase(@RequestBody StockFeignResponse request) {
        stockLockFacade.purchase(request.productId(), request.quantity());
        return Response.success();
    }

    @PostMapping("/stock/cancel")
    public Response<Void> cancel(@RequestBody StockFeignResponse request) {
        stockService.cancel(request.productId(), request.quantity());
        return Response.success();
    }
}
