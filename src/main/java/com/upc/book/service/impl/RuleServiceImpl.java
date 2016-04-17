package com.upc.book.service.impl;

import com.upc.book.rule.Item;
import com.upc.book.rule.OrderList;
import com.upc.book.entity.Order;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import com.upc.book.service.OrderService;
import com.upc.book.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by florian on 16/4/11.
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private OrderService orderService;

    //从订单表内解析出所有的项ID列表
    public OrderList createOrderList() throws BookException {
        OrderList orderList = new OrderList();
        List<Item> items = orderList.getItems();

        List<Order> allOrders = orderService.getAllOrders();
        for (Order order : allOrders) {
            Map<String, Integer> bookCountMap = Cart.getBookCountMap(order.getCart());
            Item item = new Item(bookCountMap.keySet());
            items.add(item);
        }

        return orderList;
    }
}
