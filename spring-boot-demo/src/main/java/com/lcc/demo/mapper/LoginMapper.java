package com.lcc.demo.mapper;

import com.lcc.demo.model.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LoginMapper {
    @Select("select * from login")
    List<Login> getLogin();

    @Select("select password from login where username=#{username}")
    String LoginById(@Param("username") String username);

    @Select("select username,password from login where username=#{username} and password=#{password}")
    String LoginIn(@Param("username") String username,@Param("password") String password);
}
