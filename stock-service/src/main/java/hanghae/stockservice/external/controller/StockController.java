package hanghae.stockservice.external.controller;

import hanghae.stockservice.external.controller.dto.StockDto;
import hanghae.stockservice.external.controller.dto.request.CancelRequest;
import hanghae.stockservice.external.controller.dto.request.PurchaseRequest;
import hanghae.stockservice.external.controller.dto.request.QuantityCheckRequest;
import hanghae.stockservice.external.controller.dto.response.Response;
import hanghae.stockservice.external.service.StockLockFacade;
import hanghae.stockservice.external.service.StockService;
import lombok.RequiredArgsConstructor;
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
    public Response<Void> enrollStock(@RequestBody StockDto stockDto) {
        stockService.enrollStock(stockDto);
        return Response.success();
    }

    @PostMapping("/stock/purchase")
    public Response<Void> purchase(@RequestBody PurchaseRequest request) {
        stockLockFacade.purchase(request.productId(), request.quantity());
        return Response.success();
    }

    @PostMapping("/stock/cancel")
    public Response<Void> cancel(@RequestBody CancelRequest request) {
        stockLockFacade.cancel(request.productId(), request.quantity());
        return Response.success();
    }
}
