package com.upc.book.controller;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
//拿所有书
public class BookController extends Controller {
  @RequestMapping(method = RequestMethod.GET)
  public List<Book> getBooks() throws BookException {
    return bookService.getBooks();
  }
  //查一本书信息
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public Book getBook(@PathVariable String id)
    throws BookException {
    return bookService.getBook(id);
  }
  //推上去一本书，往数据库里添加一本书
  @RequestMapping(method = RequestMethod.POST)
  public Book addBook(@RequestBody Book book)
    throws BookException {
    return bookService.saveBook(book);
  }
  //删除一本书
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public boolean deleteBook(@PathVariable String id)
    throws BookException {
    bookService.deleteBook(id);
    return true;
  }
}
