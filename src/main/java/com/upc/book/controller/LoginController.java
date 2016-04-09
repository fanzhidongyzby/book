package com.upc.book.controller;

import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class LoginController extends Controller {
    @Autowired
    HttpSession session;

    @RequestMapping(method = RequestMethod.GET)
    public String whoami() throws BookException {
        String userId = (String)session.getAttribute("user");
        return userId;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean login(@RequestBody User user) throws BookException {
        String userId = userService.validUser(user.getUserName(), user.getPassword());
        session.setAttribute("user", userId);
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean logout() throws BookException {
        session.invalidate();
        return true;
    }
}
