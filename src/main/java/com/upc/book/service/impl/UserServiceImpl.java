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
import java.util.Map;

/**
 * Created by florian on 16/4/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;


  @Override
  public String addUser(User user) throws BookException {
    if (user == null
      ||!StringUtils.hasText(user.getUserName())
      ||! StringUtils.hasText(user.getUserName())) {
      throw new BookException("user info is not valid");
    }

    user = userRepository.save(user);
    if (user == null) {
      throw new BookException("add user failed");
    }

    return user.getId();
  }

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

        return Cart.toInnerString(cart);
    }

    //把字符串转化为Cart对象
    private Cart parseCart(String value) throws BookException {

        Cart cart = new Cart();

        //根据字符串获取书id与count的映射
        Map<String, Integer> bookCountMap = Cart.getBookCountMap(value);

        for (String id : bookCountMap.keySet()) {
            int count = bookCountMap.get(id);

            //根据书id查询书的对象book
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
        //根据用户id查询用户表
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BookException("invalid user id");
        }

        //取出用户表内的cart字符串，转化为Cart对象
        Cart cart = this.parseCart(user.getCart());

        //根据书的id查询book表，获取图书
        Book book = bookService.getBook(bookId);

        if (book == null || count < 1) {
            throw new BookException("invalid book or count");
        }

        // 添加count本书到购物车
        if (!cart.addBook(book, count)) {
            return false;
        }

        //再把cart对象转化为字符串，存储到user对象
        user.setCart(this.formatCart(cart));

        //保存更新好的user对象到数据库
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
