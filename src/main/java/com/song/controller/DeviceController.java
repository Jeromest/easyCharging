package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Device;
import com.song.entity.User;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;



@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @PostMapping("create")
    public Result create(@RequestBody Device device){
        int flag = deviceService.create(device);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = deviceService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Device device){
        int flag = deviceService.update(device);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Device detail(Integer id){
        return deviceService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody Device device){
        PageInfo<Device> pageInfo = deviceService.query(device);
        return Result.ok(pageInfo);
    }

    @GetMapping("query_liver_amount")
    public Result queryLiverAmount(Integer id){
        int liverAmount = userService.queryLiverAmount(id);
        return Result.ok(liverAmount);
    }


    @GetMapping("capacity_plus_one")
    public Result capacityPlusOne(Integer id){
        int flag = deviceService.capacityPlusOne(id);
        if (flag>0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @GetMapping("capacity_minus_one")
    public Result capacityMinusOne(Integer id){
        int flag = deviceService.capacityMinusOne(id);
        if (flag>0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    @GetMapping("occupancy_rate")
    public Result occupancyRate(Integer buildingId){
        int buildingTotalStudentBedAmount = deviceService.buildingTotalStudentBedAmount(buildingId);
        int buildingActualStudentAmount = userService.buildingActualStudentAmount(buildingId);

        Double occupancyRate = (double) (buildingActualStudentAmount / buildingTotalStudentBedAmount) * 100;

        BigDecimal bd1 = new BigDecimal(occupancyRate);
        occupancyRate = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(buildingId + "使用率: " + occupancyRate + "%");

        return Result.ok(occupancyRate);

    }



    @GetMapping("/query_device_balance")
    public Result paidInfo(HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        User user1 = userService.detail(param.getId());

        // 将数据处理后四舍五入保留两位小数
        Double myDeviceBalance = deviceService.queryDeviceBalance(user1.getDeviceId());
        BigDecimal bd1 = new BigDecimal(myDeviceBalance);
        myDeviceBalance = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        // 测试
        System.out.println("myDeviceBalance");
        System.out.println("----------");
        System.out.println(myDeviceBalance);

        return Result.ok(myDeviceBalance);
    }

}
