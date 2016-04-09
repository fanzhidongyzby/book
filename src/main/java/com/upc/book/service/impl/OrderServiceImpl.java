package com.upc.book.service.impl;

import com.upc.book.entity.Order;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.repository.OrderRepository;
import com.upc.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by florian on 16/4/9.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order genOrder(User user) throws BookException {
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
}
