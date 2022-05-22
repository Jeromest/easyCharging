package com.song.controller;


import com.song.entity.Map;
import com.song.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MapController {

    @Autowired
    private MapService mapService;


    @RequestMapping("/map")
    public List<Map> queryMap() {
        return mapService.list();
    }



}
