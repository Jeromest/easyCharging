package com.song.controller;

import com.song.entity.User;
import com.song.framework.jwt.JwtUtil;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    //  从前端传入的JSON中获取对象信息
    public Result login(@RequestBody User user){

        User entity = userService.login(user.getUserName(), user.getUserPwd(), user.getUserType());
        if (entity != null){
            String token = JwtUtil.sign(entity);
            Map map = new HashMap();
            map.put(JwtUtil.token, token);
            map.put("user", entity);
            return Result.ok("登录成功", map);
        }else {
            return Result.fail("用户名或密码或角色错误");
        }
    }
}
