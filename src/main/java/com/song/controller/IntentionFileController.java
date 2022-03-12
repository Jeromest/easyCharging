package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.entity.IntentionFile;
import com.song.service.IntentionFileService;
import com.song.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/intentionFile")
public class IntentionFileController {

    @Autowired
    private IntentionFileService intentionFileService;

    @PostMapping("create")
    public Result create(@RequestBody IntentionFile intentionFile){
        int flag = intentionFileService.create(intentionFile);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = intentionFileService.delete(ids);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody IntentionFile intentionFile){
        int flag = intentionFileService.update(intentionFile);
        if(flag>0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public IntentionFile detail(Integer id){
        return intentionFileService.detail(id);
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody  IntentionFile intentionFile){
        PageInfo<IntentionFile> pageInfo = intentionFileService.query(intentionFile);
        return Result.ok(pageInfo);
    }

}
