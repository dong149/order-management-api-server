package com.github.prgrms.orders;

public class OrderRequest {

    private String message;

    public OrderRequest() {
    }

    public OrderRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
