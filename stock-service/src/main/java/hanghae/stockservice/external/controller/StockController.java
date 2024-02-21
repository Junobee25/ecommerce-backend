package hanghae.stockservice.external.controller;

import hanghae.stockservice.external.controller.dto.request.PurchaseRequest;
import hanghae.stockservice.external.controller.dto.response.Response;
import hanghae.stockservice.external.service.StockLockFacade;
import hanghae.stockservice.external.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-service")
public class StockController {

    private final StockService stockService;
    private final StockLockFacade stockLockFacade;

    @PostMapping("/stock/enroll-stock")
    public Response<Void> enrollStock(@RequestBody Long productId, Long quantity) {
        stockService.enrollStock(productId, quantity);
        return Response.success();
    }

    @PostMapping("/stock/purchase")
    public Response<Void> purchase(@RequestBody PurchaseRequest request) {
        stockLockFacade.purchase(request.productId(), request.quantity());
        return Response.success();
    }
}
