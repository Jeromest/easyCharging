package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.Bill;
import com.song.entity.User;
import com.song.service.BillService;
import com.song.service.BuildingService;
import com.song.service.DeviceService;
import com.song.service.UserService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("create")
    public Result create(@RequestBody Bill bill){
        int flag = billService.create(bill);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = billService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Bill bill){
        int flag = billService.update(bill);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Bill detail(Integer id){
        return billService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  Bill bill){
        PageInfo<Bill> pageInfo = billService.query(bill);
        return Result.ok(pageInfo);
    }


    @GetMapping("/paid_info")
    public Result paidInfo(HttpServletRequest request){
        User param = (User)request.getAttribute("user");                // ??????????????????????????????????????????
        User domer = userService.detail(param.getId());                     // ?????????????????????????????????????????????
        domer.setDevice(deviceService.detail(domer.getDeviceId()));               // ???????????????????????????????????????

        int buildingId = domer.getDevice().getBuildingId();
        System.out.println(buildingId);


        List<Integer> paidInfo = new ArrayList<>();
        paidInfo.add(billService.notPaidAmount(buildingId));
        paidInfo.add(billService.havePaidAmount(buildingId));
        paidInfo.add(billService.delayPaidAmount(buildingId));
        domer.setBillPaidInfo(paidInfo);



        List<Double> usedAndFeeInfo = new ArrayList<>();

        // ????????????????????????????????????????????????
        Double waterUsed = billService.totalWaterUsed(buildingId);
        BigDecimal bd1 = new BigDecimal(waterUsed);
        waterUsed = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        usedAndFeeInfo.add(waterUsed);

        Double waterFee = billService.totalWaterFee(buildingId);
        BigDecimal bd2 = new BigDecimal(waterFee);
        waterFee = bd2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        usedAndFeeInfo.add(waterFee);

        Double energyUsed = billService.totalEnergyUsed(buildingId);
        BigDecimal bd3 = new BigDecimal(energyUsed);
        energyUsed = bd3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        usedAndFeeInfo.add(energyUsed);

        Double energyFee = billService.totalEnergyFee(buildingId);
        BigDecimal bd4 = new BigDecimal(energyFee);
        energyFee = bd4.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        usedAndFeeInfo.add(energyFee);

        Double totalUsed = billService.totalFee(buildingId);
        BigDecimal bd5 = new BigDecimal(totalUsed);
        totalUsed = bd5.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        usedAndFeeInfo.add(totalUsed);

        domer.setBillUsedAndFeeInfo(usedAndFeeInfo);


        // ??????
        System.out.println("not paid / have paid / delay paid");
        System.out.println("----------");
        System.out.println(paidInfo);

        System.out.println("water used / water fee / energy used / energy fee / total fee");
        System.out.println("----------");
        System.out.println(usedAndFeeInfo);

        System.out.println(domer.getBillPaidInfo());
        System.out.println(domer.getBillUsedAndFeeInfo());

        return Result.ok(domer);
    }



    @GetMapping("/query_history_bill_info")
    public Result queryHistoryBillInfo(HttpServletRequest request){
        User param = (User)request.getAttribute("user");                // ??????????????????????????????????????????
        User domer = userService.detail(param.getId());                     // ?????????????????????????????????????????????
        domer.setDevice(deviceService.detail(domer.getDeviceId()));               // ?????????????????????????????????????????????

        int buildingId = domer.getDevice().getBuildingId();                   // ???????????????ID
        System.out.println(buildingId);

        int latestYear = billService.queryLatestYear();
        int latestMonth = billService.queryLatestMonth();
        System.out.println("Latest Year&Month: " + latestYear + latestMonth);

        // ???????????????????????????????????????????????????????????????????????????
        int flag = 0;


        List<Double> historyWaterUsedList = new ArrayList<>();
        List<Double> historyWaterFeeList = new ArrayList<>();
        List<Double> historyEnergyUsedList = new ArrayList<>();
        List<Double> historyEnergyFeeList = new ArrayList<>();
        List<Double> historyTotalFeeList = new ArrayList<>();
        List<Integer> historyMonthList = new ArrayList<>();


        for(int i=latestMonth; i>0; i--){
            Double historyWaterUsed = billService.historyWaterUsed(buildingId, latestYear, i);
            BigDecimal bd1 = new BigDecimal(historyWaterUsed);
            historyWaterUsed = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            historyWaterUsedList.add(historyWaterUsed);

            Double historyWaterFee = billService.historyWaterFee(buildingId, latestYear, i);
            BigDecimal bd2 = new BigDecimal(historyWaterFee);
            historyWaterFee = bd2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            historyWaterFeeList.add(historyWaterFee);

            Double historyEnergyUsed = billService.historyEnergyUsed(buildingId, latestYear, i);
            BigDecimal bd3 = new BigDecimal(historyEnergyUsed);
            historyEnergyUsed = bd3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            historyEnergyUsedList.add(historyEnergyUsed);

            Double historyEnergyFee = billService.historyEnergyFee(buildingId, latestYear, i);
            BigDecimal bd4 = new BigDecimal(historyEnergyFee);
            historyEnergyFee = bd4.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            historyEnergyFeeList.add(historyEnergyFee);

            Double historyTotalUsed = billService.historyTotalUsed(buildingId, latestYear, i);
            BigDecimal db5 = new BigDecimal(historyTotalUsed);
            historyTotalUsed = db5.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            historyTotalFeeList.add(historyTotalUsed);

            historyMonthList.add(i);
            flag ++;
        }


        int newYear = latestYear - 1;
        int newMonth = 12;
        while(flag < 6){
            historyWaterUsedList.add(billService.historyWaterUsed(buildingId, newYear, newMonth));
            historyWaterFeeList.add(billService.historyWaterFee(buildingId, newYear, newMonth));
            historyEnergyUsedList.add(billService.historyEnergyUsed(buildingId, newYear, newMonth));
            historyEnergyFeeList.add(billService.historyEnergyFee(buildingId, newYear, newMonth));
            historyTotalFeeList.add(billService.historyTotalUsed(buildingId, newYear, newMonth));
            historyMonthList.add(newMonth);
            flag ++;
            newMonth --;
        }

        System.out.println("???????????????");
        System.out.println(historyWaterUsedList);
        System.out.println(historyWaterFeeList);
        System.out.println(historyEnergyUsedList);
        System.out.println(historyEnergyFeeList);
        System.out.println(historyTotalFeeList);


        List<Object> finalList = new ArrayList<>();
        finalList.add(historyWaterUsedList);
        finalList.add(historyWaterFeeList);
        finalList.add(historyEnergyUsedList);
        finalList.add(historyEnergyFeeList);
        finalList.add(historyTotalFeeList);
        finalList.add(historyMonthList);
        System.out.println("?????????");
        System.out.println(finalList);

        return Result.ok(finalList);
    }




    //    @PostMapping("/upload")
    @PostMapping("upload")
    @ResponseBody
    public Result excelImport(@RequestParam(value = "file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        int result = 0;

        try {
            result = billService.addToDatabase(file);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (result > 0){
            System.out.println("Upload result:");
            System.out.println(result);
            return Result.ok("???????????????");
        }else {
            return Result.fail("???????????????...");
        }
    }

}
