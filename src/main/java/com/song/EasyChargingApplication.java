package com.song;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.song.mapper"})
public class EasyChargingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyChargingApplication.class, args);
    }

}
