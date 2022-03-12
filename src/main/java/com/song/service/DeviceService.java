package com.song.service;

import com.song.mapper.DeviceMapper;
import com.song.entity.Device;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;



@Service
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public int create(Device device) {
        return deviceMapper.create(device);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                deviceMapper.delete(Integer.parseInt(s));
            row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return deviceMapper.delete(id);
    }

    public int update(Device device) {
        return deviceMapper.update(device);
    }

    public int updateSelective(Device device) {
        return deviceMapper.updateSelective(device);
    }

    public PageInfo<Device> query(Device device) {
        if(device != null && device.getPage() != null){
            PageHelper.startPage(device.getPage(), device.getLimit());
        }
        return new PageInfo<Device>(deviceMapper.query(device));
    }

    public Device detail(Integer id) {
        return deviceMapper.detail(id);
    }

    public int count(Device device) {
        return deviceMapper.count(device);
    }

    public int capacityPlusOne(Integer id){
        return deviceMapper.capacityPlusOne(id);
    }

    public int capacityMinusOne(Integer id){
        return deviceMapper.capacityMinusOne(id);
    }

    public int buildingTotalStudentBedAmount(Integer buildingId){
        return deviceMapper.buildingTotalStudentBedAmount(buildingId);
    }


    public double queryDeviceBalance(Integer deviceId){
        return deviceMapper.queryDeviceBalance(deviceId);
    }


    public int changeBalance(Double newBalance, Integer deviceId){
        return deviceMapper.changeBalance(newBalance, deviceId);
    }


    public int queryTotalStuBed(Integer buildingId){
        return deviceMapper.queryTotalStuBed(buildingId);
    }


//    public int queryTotalStuDevice(Integer buildingId){
//        return deviceMapper.queryTotalStuDevice(buildingId);
//    }

    public List<Integer> queryEachStuDeviceId(Integer buildingId){
        return deviceMapper.queryEachStuDeviceId(buildingId);
    }

    public int queryEachStuDeviceFreeBedAmount(Integer deviceId){
        return deviceMapper.queryEachStuDeviceFreeBedAmount(deviceId);
    }
}
