package com.upc.book.service;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;

import java.util.List;

public interface BookService {
  public List<Book> getBooks() throws BookException;

  public Book getBook(String id) throws BookException;

  public Book saveBook(Book book) throws BookException;

  public void deleteBook(String id) throws BookException;
}
