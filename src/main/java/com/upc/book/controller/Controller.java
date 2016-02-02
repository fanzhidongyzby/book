package com.upc.book.controller;

import com.upc.book.service.BookService;
import com.upc.book.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {

  @Autowired
  BookService bookService;

}
