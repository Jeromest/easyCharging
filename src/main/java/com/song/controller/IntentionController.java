package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Intention;
import com.song.entity.User;
import com.song.service.IntentionService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @PostMapping("create")
    public Result create(@RequestBody Intention intention){
        int flag = intentionService.create(intention);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = intentionService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Intention intention){
        int flag = intentionService.update(intention);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Intention detail(Integer id){
        return intentionService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Intention intention){
        PageInfo<Intention> pageInfo = intentionService.query(intention);
        return Result.ok(pageInfo);
    }



    @PostMapping("upload")
    @ResponseBody
    public Result excelImport(@RequestParam(value = "file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        int result = 0;

        try {
            result = intentionService.addToDatabase(file);
        } catch (Exception e){
            e.printStackTrace();
        }

        List<Integer> maleAndFemaleAmountList = new ArrayList<>();

        maleAndFemaleAmountList.add(intentionService.queryFemaleAmount());
        maleAndFemaleAmountList.add(intentionService.queryMaleAmount());

        System.out.println("数量分别为：" + maleAndFemaleAmountList);

        if (result > 0){
            System.out.println("Upload result:");
            System.out.println(result);
            return Result.ok(maleAndFemaleAmountList);
        }else {
            return Result.fail("害！导入失败了...");
        }
    }


    @GetMapping("query_male_and_female_amount")
    public Result queryMaleAndFemaleAmount(){
        List<Integer> maleAndFemaleAmountList = new ArrayList<>();

        maleAndFemaleAmountList.add(intentionService.queryMaleAmount());
        maleAndFemaleAmountList.add(intentionService.queryFemaleAmount());

        System.out.println("数量分别为：" + maleAndFemaleAmountList);

        return Result.ok(maleAndFemaleAmountList);
    }


    @PostMapping("arrange")
    public Result arrange(@RequestBody  Intention intention){
        System.out.println("Intention: " + intention);
        System.out.println("MaleBuilding: " + intention.getMaleBuilding());
        System.out.println("FemaleBuilding: " + intention.getFemaleBuilding());

        int maleBuildingId = intention.getMaleBuilding();
        int femaleBuildingId = intention.getFemaleBuilding();

        List<Integer> maleDeviceList = deviceService.queryEachStuDeviceId(maleBuildingId);
        List<Integer> maleEachDeviceFreeBedList = new ArrayList<>();
        List<Integer> femaleDeviceList = deviceService.queryEachStuDeviceId(femaleBuildingId);
        List<Integer> femaleEachDeviceFreeBedList = new ArrayList<>();

        List<User> maleList = new ArrayList<>();
        List<User> femaleList = new ArrayList<>();



        for (int deviceId : maleDeviceList) {
            int deviceFreeBedAmount = deviceService.queryEachStuDeviceFreeBedAmount(deviceId);
            maleEachDeviceFreeBedList.add(deviceFreeBedAmount);
        }
        for (int deviceId : femaleDeviceList) {
            int deviceFreeBedAmount = deviceService.queryEachStuDeviceFreeBedAmount(deviceId);
            femaleEachDeviceFreeBedList.add(deviceFreeBedAmount);
        }



        Intention femaleIntention = new Intention();
        femaleIntention.setGender(0);
        PageInfo<Intention> femalePageInfo = intentionService.query(femaleIntention);

        int femaleCount = 0;
        for (int i = 0; i < femaleDeviceList.size(); i++) {
            if (femaleEachDeviceFreeBedList.get(i) != 0){
                List<Intention> list0 = null;
                if (femaleCount > femalePageInfo.getList().size() || femaleCount + femaleEachDeviceFreeBedList.get(i) > femalePageInfo.getList().size()){
                    list0 = femalePageInfo.getList().subList(femaleCount, femalePageInfo.getList().size());
                    for (Intention entity: list0) {
                        int userId = entity.getId();
                        String userPwd = "123456";                  // 设置默认密码
                        String userName = entity.getStuName();
                        int gender = entity.getGender();
                        String email = userId + "@song.edu.cn";
                        String tel = null;
                        int deviceId = femaleDeviceList.get(i);
                        int userType = 0;

                        User tempFemaleUser = new User(userId, userPwd, userName, gender, email, tel, deviceId, userType);
                        femaleList.add(tempFemaleUser);
                        System.out.println("--------");
                        System.out.println(femaleDeviceList.get(i) + "区域");
                        System.out.println("总数：" + list0);
                        System.out.println("加上一共：" + (femaleCount + list0.size()));
                    }
                    break;
                }else {
                    list0 = femalePageInfo.getList().subList(femaleCount, femaleEachDeviceFreeBedList.get(i) + femaleCount);
                    for (Intention entity: list0) {
                        int userId = entity.getId();
                        String userPwd = "123456";
                        String userName = entity.getStuName();
                        int gender = entity.getGender();
                        String email = userId + "@song.edu.cn";
                        String tel = null;
                        int deviceId = femaleDeviceList.get(i);
                        int userType = 0;

                        User tempFemaleUser = new User(userId, userPwd, userName, gender, email, tel, deviceId, userType);
                        femaleList.add(tempFemaleUser);
                    }
                }
                System.out.println("--------");
                System.out.println(femaleDeviceList.get(i) + "区域");
                System.out.println("总数：" + list0);
                femaleCount += femaleEachDeviceFreeBedList.get(i);
                System.out.println("加上共：" + femaleCount);
            }
        }



        Intention maleIntention = new Intention();
        maleIntention.setGender(1);
        PageInfo<Intention> pageInfo = intentionService.query(maleIntention);

        int count = 0;
        for (int i = 0; i < maleDeviceList.size(); i++) {
            if (maleEachDeviceFreeBedList.get(i) != 0){
                List<Intention> list = null;
                if (count > pageInfo.getList().size() || count + maleEachDeviceFreeBedList.get(i) > pageInfo.getList().size()){
                    list = pageInfo.getList().subList(count, pageInfo.getList().size());
                    break;
                }else {
                    list = pageInfo.getList().subList(count,maleEachDeviceFreeBedList.get(i) + count);
                    for (Intention entity:list) {
                        int userId = entity.getId();
                        String userPwd = "123456";
                        String userName = entity.getStuName();
                        int gender = entity.getGender();
                        String email = userId + "@song.edu.cn";
                        String tel = null;
                        int deviceId = maleDeviceList.get(i);
                        int userType = 0;

                        User tempUser = new User(userId, userPwd, userName, gender, email, tel, deviceId, userType);
                        maleList.add(tempUser);
                    }
                }

                System.out.println("--------");
                System.out.println(maleDeviceList.get(i) + "区域");
//                System.out.println("总数：" + list);
                count += maleEachDeviceFreeBedList.get(i);
//                System.out.println("加上共：" + count);
            }
        }

        int maleToUserResult = 0;
        int femaleToUserResult = 0;
        for (User user: maleList){
            userService.create(user);
            maleToUserResult ++;
        }
        for (User user: femaleList){
            userService.create(user);
            femaleToUserResult ++;
        }

        return Result.ok();
    }
}
