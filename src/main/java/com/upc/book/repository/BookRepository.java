package com.upc.book.repository;

import com.upc.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
  public List<Book> findByName(String name);
}