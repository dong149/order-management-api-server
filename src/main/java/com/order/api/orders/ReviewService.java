package com.order.api.orders;


import com.order.api.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {


    private final JdbcOrderRepository jdbcOrderRepository;
    private final JdbcReviewRepository jdbcReviewRepository;

    public ReviewService(JdbcOrderRepository jdbcOrderRepository, JdbcReviewRepository jdbcReviewRepository) {
        this.jdbcOrderRepository = jdbcOrderRepository;
        this.jdbcReviewRepository = jdbcReviewRepository;
    }

    @Transactional
    public ReviewDto submitReviewWithOrderId(Long orderId, ReviewRequest request) {
        if(request.getContent()==null)
            throw new IllegalStateException("Content Empty");
        if(request.getContent().length()>1000)
            throw new IllegalStateException("Content too long");
        // order가 존재하지 않을 경우
        Order order = jdbcOrderRepository.findById(orderId).orElseThrow(() -> {
            throw new NotFoundException("존재하지 않는 order입니다.");
        });
        // 주문 상태가 COMPLETED 여야 한다.
        if(!order.getState().equals("COMPLETED"))
            throw new IllegalStateException("Could not write review for order 1 because state(REQUESTED) is not allowed");
        // 이미 리뷰를 작성한 적이 있는 경우 예외 처리
        if(order.getReviewId()!=0) {
            System.out.println(order.getReviewId());
            throw new IllegalStateException("Could not write review for order 4 because have already written");
        }
        //save
        Review review =  jdbcReviewRepository.save(orderId, request.toEntity(order.getUserId(),order.getProductId())).orElseThrow(()->{
            throw new NotFoundException("존재하지 않는 review입니다.");
        });

        return new ReviewDto().of(review);
    }
}
