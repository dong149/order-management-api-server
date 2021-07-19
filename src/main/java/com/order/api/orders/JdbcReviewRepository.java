package com.order.api.orders;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.order.api.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_QUERY = "insert into reviews (seq,user_seq,product_seq,content) values (?,?,?,?)";
    private final String PRODUCT_UPDATE_QUERY= "update products set review_count=review_count+1 where seq=?";
    private final String ORDER_UPDATE_QUERY= "update orders set review_seq= ? where seq= ?";
    private String SELECT_QUERY = "select * from reviews where user_seq = ? AND product_seq = ?";


    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Review> save(Long orderId,Review review) {
        jdbcTemplate.update(INSERT_QUERY,null,review.getUserId(),review.getProductId(),review.getContent());
        jdbcTemplate.update(PRODUCT_UPDATE_QUERY,review.getProductId());
        List<Review> results = jdbcTemplate.query(SELECT_QUERY,mapper,review.getUserId(),review.getProductId());
        jdbcTemplate.update("update orders set review_seq= "+ results.get(0).getSeq() + " where seq = " + orderId);

        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<Review> findById(Long id) {
        String query = "select * from reviews where seq = "+id;
        List<Review> results = jdbcTemplate.query(query,mapper);
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review(
                    rs.getLong("seq"),
                    rs.getLong("user_seq"),
                    rs.getLong("product_seq"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("create_at"))
            );
}
