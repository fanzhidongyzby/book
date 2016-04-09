package com.upc.book.service.impl;

import com.upc.book.entity.Order;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.repository.OrderRepository;
import com.upc.book.service.OrderService;
import com.upc.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by florian on 16/4/9.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    @Override
    public Order genOrder(String userId) throws BookException {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new BookException("invalid user");
        }

        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Order order = new Order();
        order.setUsername(user.getUserName());
        order.setCart(user.getCart());
        order.setTimestamp(now);

        order = orderRepository.save(order);
        if (order == null) {
            throw new BookException("save order failed");
        }

        return order;
    }

    @Override
    public boolean cancelOrder(String orderId) throws BookException{
        orderRepository.delete(orderId);
        return true;
    }

    @Override
    public Order getOrder(String id) throws BookException {
        Order order = orderRepository.findOne(id);
        if (order == null) {
            throw new BookException("order not found");
        }

        return order;
    }

    @Override
    public List<Order> getOrders(String userId) throws BookException {
        return orderRepository.findByUserIdOrderByTimestampDesc(userId);
    }
}
