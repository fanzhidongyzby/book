package com.upc.book.controller;

import com.upc.book.entity.Association;
import com.upc.book.exception.BookException;
import com.upc.book.service.*;
import com.upc.book.service.impl.BookServiceImpl;
import com.upc.book.service.impl.OrderServiceImpl;
import com.upc.book.service.impl.RuleServiceImpl;
import com.upc.book.service.impl.UserServiceImpl;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class Controller {

  @Autowired
  HttpSession session;

  @Autowired
  BookService bookService;

  @Autowired
  AssociationService associationService;

  @Autowired
  UserService userService;
  @Autowired
  OrderService orderService;
  @Autowired
  RuleService ruleService;
  @Autowired
  Configuration configuration;


  String getUserId() throws BookException {
    String userId = (String)session.getAttribute("user");
    return userId;
  }
}
