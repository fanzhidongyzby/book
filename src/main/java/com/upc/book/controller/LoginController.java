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

    @RequestMapping(method = RequestMethod.GET)
    //查询，返回当前用户id，看用户是否登陆，是谁登陆
    public String whoAmI() throws BookException {
        return getUserId();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //添加一个用户
    public String register(@RequestBody User user) throws BookException {
        return userService.addUser(user);
    }
    //点登陆按钮
    @RequestMapping(method = RequestMethod.POST)
    public boolean login(@RequestBody User user) throws BookException {
        String userId = userService.validUser(user.getUserName(), user.getPassword());
        session.setAttribute("user", userId);
        return true;
    }
    //注销，把状态从服务器上删掉
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean logout() throws BookException {
        session.invalidate();
        return true;
    }
}
