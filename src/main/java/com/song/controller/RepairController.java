package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Repair;
import com.song.entity.Device;
import com.song.entity.User;
import com.song.service.BuildingService;
import com.song.service.RepairService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;



@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("create")
    public Result create(@RequestBody Repair repair){
        int flag = repairService.create(repair);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = repairService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Repair repair){
        int flag = repairService.updateSelective(repair);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Repair detail(Integer id){
        return repairService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Repair repair, HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        int userId = param.getId();

        System.out.println("ID：" + userId);
        int buildingId = buildingService.queryDomersBuilding(userId);
        repair.setBuildingId(buildingId);
        PageInfo<Repair> pageInfo = repairService.query(repair);
        pageInfo.getList().forEach(entity->{
            entity.setUser(userService.detail(entity.getStuId()));
            entity.setBuilding(buildingService.detail(entity.getBuildingId()));
            entity.setDevice(deviceService.detail(entity.getDeviceId()));
        });
        return Result.ok(pageInfo);
    }



    @PostMapping("query_my_device")
    // HttpServletRequest传入发起请求的用户信息
    public Map<String,Object> queryMyDevice(@RequestBody Repair repair, HttpServletRequest request){

        User param = (User)request.getAttribute("user");        // 获取前端发起请求的用户信息


        // repair.setDeviceId(param.getDeviceId());
                                                            // ️因返回结果显示未多个，暂时无法解决
        repair.setStuId(param.getId());
        PageInfo<Repair> pageInfo = repairService.query(repair);
        System.out.println(pageInfo);
        pageInfo.getList().forEach(entity->{
            // entity.setUser(userService.detailByDevice(entity.getDeviceId()));
            entity.setUser(userService.detail(entity.getStuId()));
            entity.setBuilding(buildingService.detail(entity.getBuildingId()));
            entity.setDevice(deviceService.detail(entity.getDeviceId()));
        });
        return Result.ok(pageInfo);
    }



//    @PostMapping("query_my_device")
//    // HttpServletRequest传入发起请求的用户信息
//    public Map<String,Object> queryMyDevice(@RequestBody Repair repair, HttpServletRequest request){
//
//        User param = (User)request.getAttribute("user");        // 获取前端发起请求的用户信息
//
//        System.out.println("User Param:" + param);
//        System.out.println("User Id:" + param.getId());
//        repair.setStuId(param.getId());
//
//        User student = userService.detail(param.getId());
//        int deviceId = student.getDeviceId();
//        repair.setDeviceId(deviceId);
//
//        PageInfo<Repair> pageInfo = repairService.query(repair);
//        System.out.println(pageInfo);
//        pageInfo.getList().forEach(entity->{
//            // entity.setUser(userService.detailByDevice(entity.getDeviceId()));
//            entity.setUser(userService.detail(entity.getStuId()));
//            entity.setBuilding(buildingService.detail(entity.getBuildingId()));
//            entity.setDevice(deviceService.detail(entity.getDeviceId()));
//        });
//        return Result.ok(pageInfo);
//    }



    @PostMapping("stu_create")
    public Result stuCreate(@RequestBody Repair repair, HttpServletRequest request){
        User param = (User)request.getAttribute("user");                // 获取发起当前操作的用户信息

        User student = new User();
        student.setId(param.getId());                                      // 构造一个新的用户，并通过前端传入的用户信息为TA的ID赋值

        PageInfo<User> pageInfo = userService.query(student);

        if (pageInfo.getList() != null && pageInfo.getList().size()>0){
            User user = pageInfo.getList().get(0);                         // 将pageInfo中数据赋值给user
            Device device = deviceService.detail(user.getDeviceId());

            repair.setBuildingId(device.getBuildingId());
            repair.setDeviceId(user.getDeviceId());
            repair.setStuId(param.getId());                                // 将“从前端获取到的用户id”赋值给repair的id
            repair.setRepStatus(0);                                        // 将维修状态置0(等待维修)
            repair.setRepDate(new Date());                                 // 将维修申报时间设定为现在

            int flag = repairService.create(repair);                       // 将上述信息打包后插入数据库
            if(flag>0){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }else {
            return Result.fail("操作失败，没有相关信息");
        }
    }

}
