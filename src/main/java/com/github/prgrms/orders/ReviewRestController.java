package com.github.prgrms.orders;

import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService){
        this.reviewService = reviewService;

    }
    @PostMapping(value = "/{id}/review")
    public ApiResult<ReviewDto> review(
            @PathVariable("id") long orderId,
            @AuthenticationPrincipal JwtAuthentication authentication,
            @Valid @RequestBody ReviewRequest request
    ){
        return success(
                reviewService.submitReviewWithOrderId(orderId,request)
        );
    }
}