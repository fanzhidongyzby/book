package com.upc.book.service;

import com.upc.book.algorithm.OrderList;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;

public interface RuleService {

    OrderList createOrderList() throws BookException;
}
