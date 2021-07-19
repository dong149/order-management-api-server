package com.order.api.orders;

import java.time.LocalDateTime;


public class ReviewDto {
    private Long seq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;



    //getter
    public Long getSeq() {
        return seq;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    //setter
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    //constructor

    public ReviewDto(){

    }

    public ReviewDto(Long seq, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }

    public ReviewDto of(Review review){
        return new ReviewDto(review.getSeq(),review.getProductId(),review.getContent(),getCreateAt());
    }





}
