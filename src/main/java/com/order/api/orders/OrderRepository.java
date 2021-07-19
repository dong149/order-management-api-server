package com.order.api.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(long id);
    List<Order> findAll(long userId,long offset,int size);
    void accept(long orderId);
    void reject(long orderId,String message);
    void shipping(long orderId);
    void complete(long orderId);
}
