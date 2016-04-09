package com.upc.book.controller;

import com.upc.book.entity.Order;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends Controller {

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getOrders() throws BookException {
        String userId = getUserId();
        return orderService.getOrders(userId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Order getOrder(@PathVariable String id) throws BookException {
        return orderService.getOrder(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean generatorOrder() throws BookException {
        return orderService.genOrder(getUserId()) != null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public boolean cancelOrder(@PathVariable String id) throws BookException {
        return orderService.cancelOrder(id);
    }
}
