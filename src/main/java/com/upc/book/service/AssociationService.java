package com.upc.book.service;

import com.upc.book.entity.Association;
import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import com.upc.book.rule.Rule;

import java.util.List;

public interface AssociationService {

    void initAssociations() throws BookException;

    List<Association> getAssociations() throws BookException;

    Association getAssociation(String id) throws BookException;

    Association saveAssociation(Association association) throws BookException;

    List<Association> saveRules(List<Rule> rules) throws BookException;

    void deleteAssociation(String id) throws BookException;

    List<Book> getAssoBooks(String id) throws BookException;
}
