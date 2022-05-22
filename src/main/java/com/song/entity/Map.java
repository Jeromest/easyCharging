package com.song.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_map")
public class Map {


    private Integer id;

    @TableField("point_lng")
    private String pointLng;		// 经度

    @TableField("point_lat")
    private  String pointLat;		// 纬度

}
