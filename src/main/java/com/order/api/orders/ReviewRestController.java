package com.order.api.orders;

import com.order.api.security.JwtAuthentication;
import com.order.api.utils.ApiUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService){
        this.reviewService = reviewService;

    }
    @PostMapping(value = "/{id}/review")
    public ApiUtils.ApiResult<ReviewDto> review(
            @PathVariable("id") long orderId,
            @AuthenticationPrincipal JwtAuthentication authentication,
            @Valid @RequestBody ReviewRequest request
    ){
        return ApiUtils.success(
                reviewService.submitReviewWithOrderId(orderId,request)
        );
    }
}