package com.upc.book.service;

import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;

public interface UserService {

    String validUser(String userName, String password) throws BookException;

    User getUser(String userId) throws BookException;

    Cart getCart(String userId) throws BookException;

    boolean addToCart(String userId, String bookId, int count) throws BookException;

    boolean removeFromCart(String userId, String bookId, int count) throws BookException;

    boolean cleanCart(String userId) throws BookException;
}
