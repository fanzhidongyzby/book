package com.upc.book.service.impl;

import com.upc.book.entity.Book;
import com.upc.book.entity.Order;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.OrderDTO;
import com.upc.book.repository.OrderRepository;
import com.upc.book.service.BookService;
import com.upc.book.service.OrderService;
import com.upc.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by florian on 16/4/9.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static String BOOK_SEP = ";";
    private static String COUNT_SEP = ":";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @Override
    public Order genOrder(String userId) throws BookException {
        User user = userService.getUser(userId);
        if (user == null || !StringUtils.hasText(user.getCart())) {
            throw new BookException("invalid user or cart");
        }

        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        Order order = new Order();
        order.setUsername(user.getId());
        order.setCart(user.getCart());
        order.setTimestamp(now);

        order = orderRepository.save(order);
        if (order == null) {
            throw new BookException("save order failed");
        }

        userService.cleanCart(userId);

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

    private List<Book> parseCartBooks(String value) throws BookException {
        if (value == null) {
            throw new BookException("invalid cart value ", value);
        }

        //获取书-数量对
        List<Book> books = new ArrayList<>();
        String[] pairs = value.split(BOOK_SEP);
        for (String pair : pairs) {
            //获取书、数量
            String[] kv = pair.split(COUNT_SEP);
            if (kv.length != 2) {
                continue;
            }
            String id = kv[0];
            int count = Integer.parseInt(kv[1]);
            books.add(bookService.getBook(id));
        }

        return books;
    }

    @Override
    public List<OrderDTO> getOrderDTOs(String userId) throws BookException {
        List<Order> orders = getOrders(userId);
        if (orders.size() > 3) {
            orders = orders.subList(0, 3);
        }

        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setBooks(parseCartBooks(order.getCart()));
            orderDTO.setTimestamp(order.getTimestamp());

            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    public List<Order> getOrders(String userId) throws BookException {
        List<Order> orders = orderRepository.findByUserIdOrderByTimestampDesc(userId);
        if (orders == null) {
            orders = new ArrayList<>();
        }

        return orders;
    }
}
