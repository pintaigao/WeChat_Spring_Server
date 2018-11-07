package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.utils.IMoocJSONResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("u")
public class UserController {

    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(@RequestBody Users user){

        // 判断用户名和密码不能为空
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            IMoocJSONResult.errorMsg("用户名或密码不能为空");
        }
        return IMoocJSONResult.ok();
    }
}
