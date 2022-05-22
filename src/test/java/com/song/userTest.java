package com.song;

import com.song.entity.Map;
import com.song.service.MapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class userTest {

    @Autowired
    private MapService mapService;

    @Test
    void queryPoint() {
        List<Map> mapList = mapService.list();
        mapList.forEach(System.out::println);
    }

    @Test
    List<Map> queryPoint2() {
       return mapService.list();
    }



}
