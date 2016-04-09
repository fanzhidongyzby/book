package com.upc.book.controller;

import com.upc.book.entity.Association;
import com.upc.book.exception.BookException;
import com.upc.book.service.AssociationService;
import com.upc.book.service.BookService;
import com.upc.book.service.OrderService;
import com.upc.book.service.UserService;
import com.upc.book.service.impl.BookServiceImpl;
import com.upc.book.service.impl.OrderServiceImpl;
import com.upc.book.service.impl.UserServiceImpl;
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


  String getUserId() throws BookException {
    String userId = (String)session.getAttribute("user");
    return userId;
  }
}
