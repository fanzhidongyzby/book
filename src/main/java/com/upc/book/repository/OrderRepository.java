package com.upc.book.repository;

import com.upc.book.entity.Order;
import com.upc.book.exception.BookException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
    List<Order> findByUserIdOrderByTimestampDesc(String userId) throws BookException;
}