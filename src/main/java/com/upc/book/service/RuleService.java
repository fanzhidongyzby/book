package com.upc.book.service;

import com.upc.book.rule.OrderList;
import com.upc.book.exception.BookException;

public interface RuleService {

    OrderList createOrderList() throws BookException;
}
