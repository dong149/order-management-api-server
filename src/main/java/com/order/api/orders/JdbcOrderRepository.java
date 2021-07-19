package com.order.api.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.order.api.utils.DateTimeUtils.dateTimeOf;
import static java.time.LocalTime.now;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String ACCEPT_QUERY = "UPDATE orders SET state=? WHERE seq=?";
    private final String REJECT_QUERY = "UPDATE orders SET state=? ,rejected_at=?,reject_msg=? WHERE seq=?";
    private final String SHIPPING_QUERY = "UPDATE orders SET state=? WHERE seq=?";
    private final String COMPLETE_QUERY = "UPDATE orders SET state=?,completed_at=? WHERE seq=?";
    private final String INSERT_QUERY = "insert into orders (seq,user_seq,product_seq,review_seq,state,request_msg,reject_msg,completed_at,rejected_at) values (?, ?, ?,?, ?, ?, ?, ?, ?)";

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> findById(long id) {
        List<Order> results = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq=" + id,
                mapper
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<Order> findAll(long userId, long offset, int size) {
        String query = "SELECT * FROM orders " + " ORDER BY seq DESC "
                + " LIMIT " + size + " OFFSET " + offset;
        return jdbcTemplate.query(
                query
                , mapper);
    }

    @Override
    public void accept(long orderId) {
        jdbcTemplate.update(ACCEPT_QUERY,"ACCEPTED",orderId);
    }

    @Override
    public void reject(long orderId,String message) {
        jdbcTemplate.update(REJECT_QUERY,"REJECTED",now(),message,orderId);
    }

    @Override
    public void shipping(long orderId) {
        jdbcTemplate.update(SHIPPING_QUERY,"SHIPPING",orderId);
    }

    @Override
    public void complete(long orderId) {
        jdbcTemplate.update(COMPLETE_QUERY,"COMPLETED",now(),orderId);
    }





    // 여기서는 객체로 가지고 있고, 저장할 때는 Long ID값으로 저장한다.
    static RowMapper<Order> mapper = (rs, rowNum) ->
        new Order(
                rs.getLong("seq"),
                rs.getLong("user_seq"),
                rs.getLong("product_seq"),
                rs.getLong("review_seq"),
                rs.getString("state"),
                rs.getString("request_msg"),
                rs.getString("reject_msg"),
                dateTimeOf(rs.getTimestamp("completed_at")),
                dateTimeOf(rs.getTimestamp("rejected_at")),
                dateTimeOf(rs.getTimestamp("create_at"))
        );

}
