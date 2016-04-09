package com.upc.book.repository;

import com.upc.book.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}