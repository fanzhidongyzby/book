package com.upc.book.service.impl;

import com.upc.book.BaseTest;
import com.upc.book.algorithm.OrderList;
import com.upc.book.exception.BookException;
import com.upc.book.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by florian on 16/4/11.
 */
public class RuleServiceImplTest extends BaseTest {


    @Autowired
    private RuleServiceImpl ruleService;

    @Override
    public void doTest() throws BookException {
        OrderList orderList = ruleService.createOrderList();
        System.out.println(orderList);
    }
}