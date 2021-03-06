package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Exchange;
import com.song.entity.Device;
import com.song.entity.User;
import com.song.service.BuildingService;
import com.song.service.ExchangeService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;



@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("create")
    public Result create(@RequestBody Exchange exchange){
        int flag = exchangeService.create(exchange);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = exchangeService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Exchange exchange){
        int flag = exchangeService.updateSelective(exchange);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Exchange detail(Integer id){
        return exchangeService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Exchange exchange){
        PageInfo<Exchange> pageInfo = exchangeService.query(exchange);
        pageInfo.getList().forEach(entity->{
            entity.setUser(userService.detail(entity.getStuId()));
            entity.setBuilding(buildingService.detail(entity.getBuildingId()));
            entity.setDevice(deviceService.detail(entity.getDeviceId()));
        });

        return Result.ok(pageInfo);
    }


    @PostMapping("query_myself")
    public Map<String, Object> queryMyself(@RequestBody Exchange exchange, HttpServletRequest request){
        User param = (User)request.getAttribute("user");

        exchange.setStuId(param.getId());
        PageInfo<Exchange> pageInfo = exchangeService.query(exchange);
        pageInfo.getList().forEach(entity->{
            entity.setUser(userService.detail(entity.getStuId()));
            entity.setBuilding(buildingService.detail(entity.getBuildingId()));
            entity.setDevice(deviceService.detail(entity.getDeviceId()));
        });
        return Result.ok(pageInfo);
    }


    @PostMapping("stu_create")
    public Result stuCreate(@RequestBody Exchange exchange, HttpServletRequest request){
        User param = (User)request.getAttribute("user");                // ???????????????????????????????????????

        User student = new User();
        student.setId(param.getId());

        PageInfo<User> pageInfo = userService.query(student);

        if (pageInfo.getList() != null && pageInfo.getList().size()>0){
            User user = pageInfo.getList().get(0);                         // ???pageInfo??????????????????user
            Device device = deviceService.detail(user.getDeviceId());              // ??????user??????ID??????device??????

            exchange.setBuildingId(device.getBuildingId());
            exchange.setDeviceId(user.getDeviceId());
            exchange.setStuId(param.getId());
            exchange.setStuName(user.getUserName());
            exchange.setGender(user.getGender());
            exchange.setExStatus(0);                                         // ??????????????????0(????????????)
            exchange.setExDate(new Date());                                 // ????????????????????????????????????

            int flag = exchangeService.create(exchange);                       // ???????????????????????????????????????
            if(flag>0){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }else {
            return Result.fail("?????????????????????????????????");
        }
    }


}
