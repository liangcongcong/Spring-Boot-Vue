package com.lcc.demo.service.impl;

import com.lcc.demo.model.Login;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginService {
    List<Login> getLogin();
    String LoginById(@Param("username") String username);
    String LoginIn(String username, String password);

}
