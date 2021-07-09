package com.github.prgrms.orders;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {

    private Long seq;

    private Long userId;

    private Long productId;

    private Long reviewId;

    private Review review;

    private String state;

    private String requestMessage;

    private String rejectMessage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createdAt;


    public OrderDto(Order order){
        copyProperties(order,this);
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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

    public Review getReview() {
        return review;
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
}
