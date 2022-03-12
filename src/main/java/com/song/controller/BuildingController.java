package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Building;
import com.song.entity.User;
import com.song.service.BuildingService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;


    @PostMapping("create")
    public Result create(@RequestBody Building building){
        int flag = buildingService.create(building);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = buildingService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Building building){
        int flag = buildingService.update(building);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Building detail(Integer id){
        return buildingService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Building building){
        PageInfo<Building> pageInfo = buildingService.query(building);


        pageInfo.getList().forEach(entity->{
            User user = userService.detail(entity.getManagerId());
            entity.setUser(user);
        });

        return Result.ok(pageInfo);
    }



    @GetMapping("query_floor_num")
    public Result queryFloorNum(Integer id){
        return Result.ok(buildingService.detail(id));
    }



    @GetMapping("occupancy_rate_and_gender")
    public Result occupancyRateAndGender(Integer buildingId){
        int buildingTotalStudentBedAmount = deviceService.buildingTotalStudentBedAmount(buildingId);
        int buildingActualStudentAmount = userService.buildingActualStudentAmount(buildingId);

        int buildingLiverGender = buildingService.queryGender(buildingId);

        Double occupancyRate = (double) buildingActualStudentAmount / buildingTotalStudentBedAmount;
        System.out.println("occupancyRate: " + occupancyRate);

        double rateTimesOneHundred = occupancyRate * 100;
        System.out.println("midFigure: " + rateTimesOneHundred);
        BigDecimal bd1 = new BigDecimal(rateTimesOneHundred);
        rateTimesOneHundred = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


        System.out.println(buildingId + "使用率: " + rateTimesOneHundred + "%");

        List<Object> occupancyRateAndGenderList = new ArrayList<>();
        occupancyRateAndGenderList.add(rateTimesOneHundred);
        occupancyRateAndGenderList.add(buildingLiverGender);

        System.out.println("occupancyRateAndGenderList: " + occupancyRateAndGenderList);
        return Result.ok(occupancyRateAndGenderList);

    }


    @PostMapping("query_male_building")
    public Map<String,Object> queryMaleBuilding(@RequestBody Building building){            // @RequestBody:将json格式的数据转为java对象
        PageInfo<Building> pageInfo = buildingService.query(building);

        pageInfo.getList().forEach(entity->{
            int buildingId = entity.getId();
            int totalStuBed = deviceService.buildingTotalStudentBedAmount(buildingId);
            int totalStuLiver = userService.buildingActualStudentAmount(buildingId);
            entity.setFreeStuBed(totalStuBed - totalStuLiver);
            System.out.println("空充电桩数量" + entity.getFreeStuBed());
        });

        return Result.ok(pageInfo);
    }


    @PostMapping("query_female_building")
    public Map<String,Object> queryFemaleBuilding(@RequestBody Building building){            // @RequestBody:将json格式的数据转为java对象
        PageInfo<Building> pageInfo = buildingService.query(building);

        pageInfo.getList().forEach(entity->{
            int buildingId = entity.getId();
            int totalStuBed = deviceService.buildingTotalStudentBedAmount(buildingId);
            int totalStuLiver = userService.buildingActualStudentAmount(buildingId);
            entity.setFreeStuBed(totalStuBed - totalStuLiver);
            System.out.println("空充电桩数量" + entity.getFreeStuBed());
        });

        return Result.ok(pageInfo);
    }


}
