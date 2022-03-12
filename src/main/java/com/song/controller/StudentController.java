package com.song.controller;

import com.song.entity.User;
import com.song.service.BuildingService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/info")
    public Result info(HttpServletRequest request){
        User param = (User)request.getAttribute("user");        // 获取前端发起请求的用户信息
        System.out.println(param);
        User student = userService.detail(param.getId());
        student.setDevice(deviceService.detail(student.getDeviceId()));
        student.setBuilding(buildingService.detail(student.getDeviceId()));
        System.out.println(student);
        return Result.ok(student);
    }

}
