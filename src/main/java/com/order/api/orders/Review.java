package com.order.api.orders;

import java.time.LocalDateTime;

public class Review {
//    CREATE TABLE reviews (
//            seq           bigint NOT NULL AUTO_INCREMENT, --리뷰 PK
//  user_seq      bigint NOT NULL,                --리뷰 작성자 PK (users 테이블 참조)
//    product_seq   bigint NOT NULL,                --리뷰 상품 PK (products 테이블 참조)
//    content       varchar(1000) NOT NULL,         --리뷰 내용
//    create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
//    PRIMARY KEY (seq),
//    CONSTRAINT fk_reviews_to_users FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
//    CONSTRAINT fk_reviews_to_products FOREIGN KEY (product_seq) REFERENCES products (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
//);

    private Long seq;

    private Long userId;

    private Long productId;

    private String content;

    private LocalDateTime createAt;


    //getter
    public Long getSeq() {
        return seq;
    }

    public Long getUserId() {
        return userId;
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

    //constructor
    public Review(Long seq, Long userId, Long productId, String content) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }

    public Review(Long seq, Long userId, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }
}
