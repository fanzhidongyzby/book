package com.upc.book.service.impl;

import com.upc.book.entity.Book;
import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.BookCount;
import com.upc.book.pojo.Cart;
import com.upc.book.repository.UserRepository;
import com.upc.book.service.BookService;
import com.upc.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by florian on 16/4/9.
 */
@Service
public class UserServiceImpl implements UserService {

    private static String BOOK_SEP = ";";
    private static String COUNT_SEP = ":";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;


    @Override
    public String validUser(String userName, String password) throws BookException {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            throw new BookException("invalid username or password");
        }

        User user = userRepository.findByUserName(userName);
        if (user == null || !password.equals(user.getPassword())) {
            throw new BookException("invalid username or password");
        }

        return user.getId();
    }

    //把字符串转化为Cart对象
    private Cart parseCart(String value) throws BookException {
        if (!StringUtils.hasText(value)) {
            throw new BookException("invalid cart value ", value);
        }

        //获取书-数量对
        String[] pairs = value.split(BOOK_SEP);
        if (pairs.length == 0) {
            throw new BookException("invalid cart value ", value);
        }

        Cart cart = new Cart();
        for (String pair : pairs) {
            //获取书、数量
            String[] kv = pair.split(COUNT_SEP);
            if (kv.length > 2 || kv.length < 1) {
                throw new BookException("invalid cart value ", value);
            }
            String id = kv[0];
            int count = 1;
            if (kv.length > 1) {
                count = Integer.parseInt(kv[1]);
            }
            BookCount bookCount = new BookCount();
            bookCount.setBook(bookService.getBook(id));
            bookCount.setCount(count);

            //写入Cart对象
            cart.getBookCounts().add(bookCount);
        }

        return cart;
    }

    @Override
    public Cart getCart(String userId) throws BookException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("invalid user id");
        }

        //解析cart字符串到对象
        return this.parseCart(user.getCart());
    }
}
