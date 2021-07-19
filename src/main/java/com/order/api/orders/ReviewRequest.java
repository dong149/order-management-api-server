package com.order.api.orders;

public class ReviewRequest {
    private String content;



    public String getContent() {
        return content;
    }

    public ReviewRequest() {}

    public ReviewRequest(String content) {
        this.content = content;
    }

    public Review toEntity(Long userId,Long productId){
        return new Review(null,userId,productId,this.content);
    }
}
