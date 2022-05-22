package com.song.mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.entity.Building;


public interface BuildingMapper {

	public int create(Building building);

	public int delete(Integer id);

	public int update(Building building);

	public int updateSelective(Building building);

	public List<Building> query(Building building);

	public Building detail(Integer id);

	public int count(Building building);

	public int queryAddress(Integer buildingId);

	public int queryDomersBuilding(Integer userId);

}