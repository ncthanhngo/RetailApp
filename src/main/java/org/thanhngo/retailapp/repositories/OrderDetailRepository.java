package org.thanhngo.retailapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thanhngo.retailapp.models.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(Long orderId);
}
