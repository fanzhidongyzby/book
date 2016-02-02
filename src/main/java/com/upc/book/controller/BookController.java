package com.upc.book.controller;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController extends Controller {
  @RequestMapping(method = RequestMethod.GET)
  public List<Book> getBooks() throws BookException {
    return bookService.getBooks();
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public Book getBook(@PathVariable String id)
    throws BookException {
    return bookService.getBook(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Book addBook(@RequestBody Book book)
    throws BookException {
    return bookService.saveBook(book);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public boolean deleteBook(@PathVariable String id)
    throws BookException {
    bookService.deleteBook(id);
    return true;
  }
}
