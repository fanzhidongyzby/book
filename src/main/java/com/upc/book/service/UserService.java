package com.upc.book.service;

import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;

public interface UserService {

    String validUser(String userName, String password) throws BookException;

    Cart getCart(String userId) throws BookException;

}
