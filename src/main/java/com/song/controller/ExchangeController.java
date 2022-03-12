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
        User param = (User)request.getAttribute("user");                // 获取发起当前操作的用户信息

        User student = new User();
        student.setId(param.getId());

        PageInfo<User> pageInfo = userService.query(student);

        if (pageInfo.getList() != null && pageInfo.getList().size()>0){
            User user = pageInfo.getList().get(0);                         // 将pageInfo中数据赋值给user
            Device device = deviceService.detail(user.getDeviceId());              // 通过user房间ID获取device信息

            exchange.setBuildingId(device.getBuildingId());
            exchange.setDeviceId(user.getDeviceId());
            exchange.setStuId(param.getId());
            exchange.setStuName(user.getUserName());
            exchange.setGender(user.getGender());
            exchange.setExStatus(0);                                         // 将调换状态置0(等待审核)
            exchange.setExDate(new Date());                                 // 将调换申报时间设定为现在

            int flag = exchangeService.create(exchange);                       // 将上述信息打包后插入数据库
            if(flag>0){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }else {
            return Result.fail("操作失败，缺少相关信息");
        }
    }


}
