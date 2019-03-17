package com.lcc.demo.controller;

import com.lcc.demo.mapper.LoginMapper;
import com.lcc.demo.model.Login;

import com.lcc.demo.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /* @RequestMapping(value ="/login", method = RequestMethod.POST)
     public  List<Login> UserLogin(){
       List<Login> list=loginService.getLogin();
       return list;*/

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Boolean LoginIn(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("你输入的用户名为：" + username);
        System.out.println("你输入的密码为：" + password);
        String tname = loginService.LoginIn(username, password);
        session.setAttribute("tname", tname);
        if (tname == null) {
            return false;
        } else {
            return true;
        }

    }

}