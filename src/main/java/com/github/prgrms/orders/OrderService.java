package com.github.prgrms.orders;


import com.github.prgrms.configures.web.SimplePageRequest;
import com.github.prgrms.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;


    public OrderService(OrderRepository orderRepository, ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAll(SimplePageRequest simplePageRequest, Long userId) {
        List<Order> orderList = orderRepository.findAll(userId, simplePageRequest.getOffset(), simplePageRequest.getSize());
        List<OrderDto> orderDtoList = orderList.stream().map(OrderDto::new).collect(toList());

        for (OrderDto o : orderDtoList) {
            o.setReview(reviewRepository.findById(o.getReviewId()).orElse(null));
        }
        return orderDtoList;
    }

    @Transactional(readOnly = true)
    public OrderDto findById(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("order not found");
        });

        if (!order.getUserId().equals(userId))
            throw new IllegalStateException("Could not get order because userId is not equal");

        OrderDto orderDto = new OrderDto(order);

        orderDto.setReview(reviewRepository.findById(order.getReviewId()).orElse(null));
        return orderDto;
    }


    @Transactional
    public boolean accept(Long orderId, Long userId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("order not found");
        });

        if(!order.getState().equals("REQUESTED"))
            return false;

        orderRepository.accept(orderId);
        return true;
    }

    @Transactional
    public boolean reject(Long orderId, Long userId, String message){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("order not found");
        });

        if(!order.getState().equals("REQUESTED"))
            return false;

        orderRepository.reject(orderId,message);
        return true;
    }

    @Transactional
    public boolean shipping(Long orderId, Long userId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("order not found");
        });

        if(!order.getState().equals("ACCEPTED"))
            return false;

        orderRepository.shipping(orderId);
        return true;
    }

    @Transactional
    public boolean complete(Long orderId, Long userId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("order not found");
        });

        if(!order.getState().equals("SHIPPING"))
            return false;

        orderRepository.complete(orderId);
        return true;
    }





}
