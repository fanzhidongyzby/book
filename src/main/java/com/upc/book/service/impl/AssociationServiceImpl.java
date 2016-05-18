package com.upc.book.service.impl;

import com.upc.book.entity.Association;
import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import com.upc.book.repository.AssociationRepository;
import com.upc.book.rule.Item;
import com.upc.book.rule.Rule;
import com.upc.book.service.AssociationService;
import com.upc.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationServiceImpl implements AssociationService {


  @Autowired
  private AssociationRepository associationRepository;

  @Autowired
  private BookService bookService;


  @Override
  public void initAssociations() throws BookException {
    Object ys = associationRepository.getYFromX("10");
    System.out.print(ys);
  }

  @Override
  public List<Association> getAssociations() throws BookException {
    return associationRepository.findAll();
  }

  public List<Book> getAssoBooks(String id) throws BookException {
    List<String>  assoIds = associationRepository.getYFromX(id);

    if (assoIds.size() > 3) {
      assoIds = assoIds.subList(0, 3);
    }

    List<Book> assoBooks = new ArrayList<>();
    for (String assoId : assoIds) {
      Book assoBook = bookService.getBook(assoId);
      assoBooks.add(assoBook);
    }

    return assoBooks;
  }

  @Override
  public Association getAssociation(String id) throws BookException {
    return null;
  }

  @Override
  public Association saveAssociation(Association association) throws BookException {
    return null;
  }

  @Override
  //
  public List<Association> saveRules(List<Rule> rules) throws BookException {
    List<Association> associations = new ArrayList<>();
    for (Rule rule : rules) {
      Item left = rule.getLeft();
      Item right = rule.getRight();
      double confidence = rule.getConfidence();
      if (left.getElements().size() == 1 && right.getElements().size() == 1) {
        Association association = new Association();
        association.setX(left.getElements().first());
        association.setY(right.getElements().first());
        association.setPercent(confidence);

        associations.add(association);
      }
    }

    associationRepository.deleteAll();
    associations = associationRepository.save(associations);
    if (associations == null) {
      throw new BookException("save rules failed");
    }

    return associations;
  }

  @Override
  public void deleteAssociation(String id) throws BookException {
    associationRepository.delete(id);
  }

  @Override
  public void clearAssociation() throws BookException {
    associationRepository.deleteAll();
  }
}
