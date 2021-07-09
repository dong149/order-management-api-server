package com.github.prgrms.orders;

import com.github.prgrms.configures.web.SimplePageRequest;
import com.github.prgrms.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;
import static com.google.common.base.Preconditions.checkArgument;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ApiResult<List<OrderDto>> findAll(
            @AuthenticationPrincipal JwtAuthentication authentication,
            SimplePageRequest simplePageRequest
    ){
        return success(orderService.findAll(simplePageRequest,authentication.id));
    }

    @GetMapping("{id}")
    public ApiResult<OrderDto> findById(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return success(orderService.findById(id,authentication.id));

    }

    @PatchMapping("{id}/accept")
    public ApiResult<Boolean> accept(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return success(orderService.accept(id,authentication.id));
    }


    @PatchMapping("{id}/reject")
    public ApiResult<Boolean> reject(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id,
            @RequestBody(required = false) OrderRequest orderRequest
    ){
        if(orderRequest==null)
            throw new IllegalArgumentException("orderRequest empty");
        return success(orderService.reject(id,authentication.id,orderRequest.getMessage()));
    }

    @PatchMapping("{id}/shipping")
    public ApiResult<Boolean> shipping(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return success(orderService.shipping(id,authentication.id));
    }

    @PatchMapping("{id}/complete")
    public ApiResult<Boolean> complete(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long id
    ){
        return success(orderService.complete(id,authentication.id));
    }



}