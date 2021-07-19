package com.order.api.orders;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewRepository {

    @Transactional
    Optional<Review> save(Long orderId,Review review);


    @Transactional(readOnly = true)
    Optional<Review> findById(Long id);
}
