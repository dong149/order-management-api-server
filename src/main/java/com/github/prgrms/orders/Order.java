package com.github.prgrms.orders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Order {

    private Long seq;

    private Long userId;

    private Long productId;

    private Long reviewId;

    private String state;

    private String requestMessage;

    private String rejectMessage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createdAt;

    public Long getSeq() {
        return seq;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getState() {
        return state;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Order(Long seq, Long userId, Long productId, Long reviewId, String state, String requestMessage, String rejectMessage, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createdAt) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.reviewId = reviewId;
        this.state = state;
        this.requestMessage = requestMessage;
        this.rejectMessage = rejectMessage;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createdAt = createdAt;
    }
}
