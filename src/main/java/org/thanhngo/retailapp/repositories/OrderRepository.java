package org.thanhngo.retailapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thanhngo.retailapp.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //Find order by user id
    List<Order> findByUserId(Long userId);
}
