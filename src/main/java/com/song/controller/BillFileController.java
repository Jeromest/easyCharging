package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.BillFile;
import com.song.service.BillFileService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/billFile")
public class BillFileController {

    @Autowired
    private BillFileService billFileService;

    @PostMapping("create")
    public Result create(@RequestBody BillFile billFile){
        int flag = billFileService.create(billFile);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = billFileService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody BillFile billFile){
        int flag = billFileService.update(billFile);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public BillFile detail(Integer id){
        return billFileService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  BillFile billFile){
        PageInfo<BillFile> pageInfo = billFileService.query(billFile);
        return Result.ok(pageInfo);
    }

}
