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

import java.util.List;

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

    @Override
    public User getUser(String userId) throws BookException {

        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("get user failed");
        }

        return user;
    }

    //把Cart对象转化为字符串
    private String formatCart(Cart cart) throws BookException {
        if (cart == null || cart.getBookCounts() == null) {
            throw new BookException("invalid cart");
        }

        StringBuffer stringBuffer = new StringBuffer();
        List<BookCount> bookCounts = cart.getBookCounts();
        for (BookCount bookCount : bookCounts) {
            Book book = bookCount.getBook();
            int count = bookCount.getCount();
            if (book == null || count < 1) {
                throw new BookException("invalid cart");
            }

            stringBuffer.append(book.getId() + COUNT_SEP + count + BOOK_SEP);
        }

        return stringBuffer.toString();
    }

    //把字符串转化为Cart对象
    private Cart parseCart(String value) throws BookException {
        if (value == null) {
            throw new BookException("invalid cart value ", value);
        }

        //获取书-数量对
        Cart cart = new Cart();
        String[] pairs = value.split(BOOK_SEP);
        for (String pair : pairs) {
            //获取书、数量
            String[] kv = pair.split(COUNT_SEP);
            if (kv.length != 2) {
                continue;
            }
            String id = kv[0];
            int count = Integer.parseInt(kv[1]);
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

    @Override
    public boolean addToCart(String userId, String bookId, int count) throws BookException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("invalid user id");
        }

        Cart cart = this.parseCart(user.getCart());
        Book book = bookService.getBook(bookId);

        if (book == null || count < 1) {
            throw new BookException("invalid book or count");
        }

        if (!cart.addBook(book, count)) {
            return false;
        }

        user.setCart(this.formatCart(cart));
        user = userRepository.save(user);
        if (user == null) {
            throw new BookException("add cart failed");
        }

        return true;
    }

    @Override
    public boolean removeFromCart(String userId, String bookId, int count) throws BookException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("invalid user id");
        }

        Cart cart = this.parseCart(user.getCart());
        Book book = bookService.getBook(bookId);

        if (book == null || count < 1) {
            throw new BookException("invalid book or count");
        }

        if (!cart.removeBook(book, count)) {
            return false;
        }

        user.setCart(this.formatCart(cart));
        user = userRepository.save(user);
        if (user == null) {
            throw new BookException("remove cart failed");
        }

        return true;
    }

    @Override
    public boolean cleanCart(String userId) throws BookException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("invalid user id");
        }

        user.setCart("");
        user = userRepository.save(user);
        if (user == null) {
            throw new BookException("remove cart failed");
        }
        return true;
    }

}
