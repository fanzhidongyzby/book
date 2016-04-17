package com.upc.book;

import com.upc.book.exception.BookException;
import com.upc.book.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationConfig.class, args);
//    BookService bookService = ctx.getBean(BookService.class);
//    try {
//      bookService.initBooks();
//    } catch (BookException e) {
//      e.printStackTrace();
//    }
    System.out.println("Book Server Start Success !");
  }
}
