package com.upc.book.service.impl;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import com.upc.book.repository.BookRepository;
import com.upc.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


  @Autowired
  private BookRepository bookRepository;

  public List<Book> getBooks() throws BookException {
    return bookRepository.findAll();
  }

  public Book getBook(String id) throws BookException {
    return bookRepository.findOne(id);
  }

  public Book saveBook(Book book) throws BookException {
    return bookRepository.save(book);
  }

  public void deleteBook(String id) throws BookException {
    bookRepository.delete(id);
  }
}
