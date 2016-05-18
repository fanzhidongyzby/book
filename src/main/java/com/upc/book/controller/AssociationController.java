package com.upc.book.controller;

import com.upc.book.entity.Association;
import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liuxin on 16/3/16.
 */
@RestController
@RequestMapping("/api/associations")
//拿到所有关联信息 （）
public class AssociationController extends Controller {
  @RequestMapping(method = RequestMethod.GET)
  public List<Association> getAll() throws BookException {
    return associationService.getAssociations();
  }
//拿到所有关联书
  @RequestMapping(value = "books", method = RequestMethod.GET)
  public List<Book> getAssoBook(@RequestParam String id) throws BookException {
    return associationService.getAssoBooks(id);
  }
}
