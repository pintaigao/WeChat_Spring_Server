package com.imooc.service;

import com.imooc.pojo.Users;

public interface UserService {

    /* 判断用户名是否存在 */
    boolean queryUsernameIsExist(String username);

    /* 查询用户是否存在*/
    Users queryUserForLogin(String username,String pwd);

    /* 用户注册 */
    Users saveUser(Users user);



}
