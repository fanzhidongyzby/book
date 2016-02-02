package com.upc.book;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationConfig.class, args);
    System.out.println("Book Server Start Success !");
  }
}
