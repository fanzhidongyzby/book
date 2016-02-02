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

  public void initBooks() throws BookException {
    Book book1 = new Book();
    book1.setNumber("NBJ-33JJH-NJH-LPJ1234");
    book1.setName("程序员的自我修养");
    book1.setAuthor("俞甲子 石凡");
    book1.setPrice(45.90);
    book1.setDescription("一本描述程序工作原理的书");

    Book book2 = new Book();
    book2.setNumber("NBJ-33JJHKJ-NJH-LPJ1234");
    book2.setName("人类简史");
    book2.setAuthor("尤瓦尔");
    book2.setPrice(68.00);
    book2.setDescription("销量第一的社科读物");

    this.saveBook(book1);
    this.saveBook(book2);
  }

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
