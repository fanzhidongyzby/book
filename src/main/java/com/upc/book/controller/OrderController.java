package com.upc.book.controller;

import com.upc.book.entity.Order;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import com.upc.book.pojo.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends Controller {

    @RequestMapping(method = RequestMethod.GET)
    //获取所有订单
    public List<OrderDTO> getOrders() throws BookException {
        String userId = getUserId();
        return orderService.getOrderDTOs(userId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    //拿某一个订单具体信息（）
    public Order getOrder(@PathVariable String id) throws BookException {
        return orderService.getOrder(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    //从购物车里生成一个订单
    public boolean generatorOrder() throws BookException {
        //生成订单，将用户表的cart字段拷贝到order表（会创建一个新的订单的记录）
        return orderService.genOrder(getUserId()) != null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    //删除订单（）
    public boolean cancelOrder(@PathVariable String id) throws BookException {
        return orderService.cancelOrder(id);
    }
}
