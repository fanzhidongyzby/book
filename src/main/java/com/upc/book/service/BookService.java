package com.upc.book.service;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;

import java.util.List;

public interface BookService {

    void initBooks() throws BookException;

    List<Book> getBooks() throws BookException;

    boolean hasBook(String id) throws BookException;

    Book getBook(String id) throws BookException;

    Book saveBook(Book book) throws BookException;

    void deleteBook(String id) throws BookException;
}
