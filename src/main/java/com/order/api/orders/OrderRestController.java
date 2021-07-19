package com.order.api.orders;

import com.order.api.configures.web.SimplePageRequest;
import com.order.api.security.JwtAuthentication;
import com.order.api.utils.ApiUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ApiUtils.ApiResult<List<OrderDto>> findAll(
            @AuthenticationPrincipal JwtAuthentication authentication,
            SimplePageRequest simplePageRequest
    ){
        return ApiUtils.success(orderService.findAll(simplePageRequest,authentication.id));
    }

    @GetMapping("{id}")
    public ApiUtils.ApiResult<OrderDto> findById(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return ApiUtils.success(orderService.findById(id,authentication.id));

    }

    @PatchMapping("{id}/accept")
    public ApiUtils.ApiResult<Boolean> accept(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return ApiUtils.success(orderService.accept(id,authentication.id));
    }


    @PatchMapping("{id}/reject")
    public ApiUtils.ApiResult<Boolean> reject(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id,
            @RequestBody(required = false) OrderRequest orderRequest
    ){
        if(orderRequest==null)
            throw new IllegalArgumentException("orderRequest empty");
        return ApiUtils.success(orderService.reject(id,authentication.id,orderRequest.getMessage()));
    }

    @PatchMapping("{id}/shipping")
    public ApiUtils.ApiResult<Boolean> shipping(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return ApiUtils.success(orderService.shipping(id,authentication.id));
    }

    @PatchMapping("{id}/complete")
    public ApiUtils.ApiResult<Boolean> complete(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return ApiUtils.success(orderService.complete(id,authentication.id));
    }



}