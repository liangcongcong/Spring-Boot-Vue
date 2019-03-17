package com.lcc.demo.service.impl.implement;

import com.lcc.demo.mapper.LoginMapper;
import com.lcc.demo.model.Login;;
import com.lcc.demo.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginServiceimpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String LoginById(String username) {
         return  loginMapper.LoginById(username);
    }
    @Override
    public List<Login> getLogin() {

        List<Login> loginList = loginMapper.getLogin();

        // lamdba表达式的使用

        // List<User> userListOrderBy= userList.stream().filter((User u) -> u.getName() == "1212").collect(Collectors.toList());

        return loginList;
    }
    @Override
    public String LoginIn(String username, String password)
    {
        return loginMapper.LoginIn(username,password);
    };
}
