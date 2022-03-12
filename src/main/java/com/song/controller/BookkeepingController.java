package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Bookkeeping;
import com.song.entity.User;
import com.song.service.BookkeepingService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/bookkeeping")
public class BookkeepingController {

    @Autowired
    private BookkeepingService bookkeepingService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;


    @PostMapping("create")
    public Result create(@RequestBody Bookkeeping bookkeeping, HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        int userId = param.getId();     // 1. 获取该用户Id

        User user = userService.detail(userId);
        int deviceId = user.getDeviceId();

        bookkeeping.setDeviceId(deviceId);
        bookkeeping.setUserId(userId);
        bookkeeping.setBkTime(new Date());


        double newBalance = deviceService.queryDeviceBalance(deviceId);
        System.out.println("原始余额：" + newBalance);
        if(bookkeeping.getBkType()==0){
            newBalance = newBalance - bookkeeping.getBkMoney();     // 支出
        }else {
            newBalance = newBalance + bookkeeping.getBkMoney();     // 收入
        }
        System.out.println("当前余额：" + newBalance);
        int flagger = deviceService.changeBalance(newBalance, deviceId);    // 修改余额
        System.out.println("余额修改状态：（1成功，0失败）" + flagger);

        int flag = bookkeepingService.create(bookkeeping);              // 创建账目
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = bookkeepingService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Bookkeeping bookkeeping){
        int flag = bookkeepingService.update(bookkeeping);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Bookkeeping detail(Integer id){
        return bookkeepingService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Bookkeeping bookkeeping){
        PageInfo<Bookkeeping> pageInfo = bookkeepingService.query(bookkeeping);
        return Result.ok(pageInfo);
    }




    @PostMapping("query_my_device")
    public Map<String, Object> queryMyDevice(@RequestBody Bookkeeping bookkeeping ,HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        int userId = param.getId();     // 1. 获取该用户Id

        User user = userService.detail(userId);
        int deviceId = user.getDeviceId();

        bookkeeping.setDeviceId(deviceId);
        PageInfo<Bookkeeping> pageInfo = bookkeepingService.queryMyDevice(bookkeeping);

        pageInfo.getList().forEach(entity->{
            entity.setUser(userService.detail(entity.getUserId()));
        });

        System.out.println("My Device Bookkeeping Details:");
        System.out.println(pageInfo);
        return Result.ok(pageInfo);
    }




    // 旭日图中使用该数据
    @PostMapping("query_my_device_expenditure")
    public Map<String, Object> queryMyDeviceExpenditure(HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        int userId = param.getId();     // 1. 获取该用户Id

        PageInfo<Bookkeeping> pageInfo = bookkeepingService.queryMyDeviceExpenditure(userId);

        pageInfo.getList().forEach(entity->{


            entity.setUser(userService.detail(entity.getUserId()));
        });

        System.out.println("My Device Bookkeeping Expenditure Details:");
        System.out.println(pageInfo);
        return Result.ok(pageInfo);
    }


    @PostMapping("query_my_device_each_classification_amount")
    public Result queryMyDeviceEachClassificationAmount(HttpServletRequest request){
        User param = (User)request.getAttribute("user");
        int userId = param.getId();     // 1. 获取该用户Id

        List<Integer> list = new ArrayList<>();
        list = bookkeepingService.queryMyDeviceEachClassificationAmount(userId);

        System.out.println("每类支出的数量分别是：");
        System.out.println(list);
        return Result.ok(list);
    }



    // 堆叠图中使用该数据
    @GetMapping("/query_my_device_each_month_bk_info")
    public Result queryMyDeviceEachMonthBkInfo(HttpServletRequest request){
        User param = (User)request.getAttribute("user");                // 获取发起当前操作的用户信息
        User domer = userService.detail(param.getId());
        int deviceId = domer.getDeviceId();

        System.out.println(deviceId);

        Date latestDate = bookkeepingService.queryMaxDate(deviceId);
        System.out.println("最新日期：");
        System.out.println(latestDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormatted=sdf.format(latestDate);
        System.out.println("格式化字符串：");
        System.out.println(dateFormatted);


        int latestYear = Integer.parseInt(dateFormatted.substring(0, 4));
        int latestMonth = Integer.parseInt(dateFormatted.substring(5, 7));
        System.out.println("年：月：");
        System.out.println(latestYear);
        System.out.println(latestMonth);

        List<Double> watergyBkList = new ArrayList<>();
        List<Double> foodBkList = new ArrayList<>();
        List<Double> bookBkList = new ArrayList<>();
        List<Double> entertainmentBkList = new ArrayList<>();
        List<Double> movieBkList = new ArrayList<>();
        List<Double> oerBkList = new ArrayList<>();
        List<Integer> monthList = new ArrayList<>();


        // 近三个月的支出数据
        for(int i=latestMonth; i>latestMonth-4; i--){
            double watergy = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 0);
            watergyBkList.add(watergy);

            double food = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 1);
            foodBkList.add(food);

            double book = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 2);
            bookBkList.add(book);

            double entertainment = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 3);
            entertainmentBkList.add(entertainment);

            double movie = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 4);
            movieBkList.add(movie);

            double oer = bookkeepingService.queryMonthlyExpenditureByClassification(deviceId, latestYear, i, 5);
            oerBkList.add(oer);

            monthList.add(i);
        }


        System.out.println("每月每类支出列表：");
        System.out.println(watergyBkList);
        System.out.println(foodBkList);
        System.out.println(bookBkList);
        System.out.println(entertainmentBkList);
        System.out.println(movieBkList);
        System.out.println(oerBkList);

        List<Object> finalList = new ArrayList<>();
        finalList.add(watergyBkList);
        finalList.add(foodBkList);
        finalList.add(bookBkList);
        finalList.add(entertainmentBkList);
        finalList.add(movieBkList);
        finalList.add(oerBkList);
        finalList.add(monthList);


        System.out.println("FinalBookkeepingList: ");
        System.out.println(finalList);
        return Result.ok(finalList);
    }
}
