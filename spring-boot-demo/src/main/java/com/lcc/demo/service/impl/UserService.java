package com.lcc.demo.service.impl;

import com.lcc.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    void AddUser(User user);

    void delUserById(@Param("no") int no);

    void updateUserByNo(User user);

    List<User> getUser();
}
