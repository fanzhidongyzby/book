package com.upc.book.controller;

import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import com.upc.book.pojo.Cart;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController extends Controller {

    @RequestMapping(method = RequestMethod.GET)
    public Cart getCart() throws BookException {
        String userId = getUserId();
        return userService.getCart(userId);
    }

    @RequestMapping(value = "/inc", method = RequestMethod.POST)
    public boolean increase(@RequestParam String id, @RequestParam int count) throws BookException {
        return userService.addToCart(getUserId(), id, count);
    }

    @RequestMapping(value = "/dec", method = RequestMethod.POST)
    public boolean decrease(@RequestParam String id, @RequestParam int count) throws BookException {
        return userService.addToCart(getUserId(), id, count);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean clean() throws BookException {
        return userService.cleanCart(getUserId());
    }
}
