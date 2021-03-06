package com.upc.book.service;

import com.upc.book.entity.Order;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import com.upc.book.pojo.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrderDTOs(String userId) throws BookException;

    List<Order> getOrders(String userId) throws BookException;

    List<Order> getAllOrders() throws BookException;

    Order genOrder(String userId) throws BookException;

    boolean cancelOrder(String orderId) throws BookException;

    Order getOrder(String id) throws BookException;
}
