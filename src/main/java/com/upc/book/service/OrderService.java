package com.upc.book.service;

import com.upc.book.entity.Order;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;

public interface OrderService {

    Order genOrder(User user) throws BookException;

}
