package com.song.service;

import com.song.mapper.BuildingMapper;
import com.song.entity.Building;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DeviceService deviceService;

    public int create(Building building) {
        return buildingMapper.create(building);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                buildingMapper.delete(Integer.parseInt(s));
            row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return buildingMapper.delete(id);
    }

    public int update(Building building) {
        return buildingMapper.update(building);
    }

    public int updateSelective(Building building) {
        return buildingMapper.updateSelective(building);
    }

    public PageInfo<Building> query(Building building) {
        if(building != null && building.getPage() != null){
            PageHelper.startPage(building.getPage(),building.getLimit());
        }
        return new PageInfo<Building>(buildingMapper.query(building));
    }

    public Building detail(Integer id) {
        return buildingMapper.detail(id);
    }

    public int count(Building building) {
        return buildingMapper.count(building);
    }

    public int queryGender(Integer buildingId){
        return buildingMapper.queryAddress(buildingId);
    }

    public int queryDomersBuilding(Integer userId){
        return buildingMapper.queryDomersBuilding(userId);
    }
}