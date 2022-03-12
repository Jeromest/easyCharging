package com.song.mapper;

import java.util.List;

import com.song.entity.Device;


public interface DeviceMapper {

	public int create(Device device);

	public int delete(Integer id);

	public int update(Device device);

	public int updateSelective(Device device);

	public int capacityPlusOne(Integer id);

	public int capacityMinusOne(Integer id);

	public List<Device> query(Device device);

	public Device detail(Integer id);

	public int count(Device device);


	public int buildingTotalStudentBedAmount(Integer buildingId);

	public double queryDeviceBalance(Integer deviceId);

	public int changeBalance(Double newBalance, Integer deviceId);

	public int queryTotalStuBed(Integer buildingId);

//	public int queryTotalStuDevice(Integer buildingId);

	public List<Integer> queryEachStuDeviceId(Integer buildingId);

	public int queryEachStuDeviceFreeBedAmount(Integer deviceId);



}