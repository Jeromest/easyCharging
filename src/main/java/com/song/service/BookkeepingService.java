package com.song.service;

import com.song.mapper.BookkeepingMapper;
import com.song.entity.Bookkeeping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class BookkeepingService {

    @Autowired
    private BookkeepingMapper bookkeepingMapper;

    public int create(Bookkeeping bookkeeping) {
        return bookkeepingMapper.create(bookkeeping);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                bookkeepingMapper.delete(Integer.parseInt(s));
            row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return bookkeepingMapper.delete(id);
    }

    public int update(Bookkeeping bookkeeping) {
        return bookkeepingMapper.update(bookkeeping);
    }

    public int updateSelective(Bookkeeping bookkeeping) {
        return bookkeepingMapper.updateSelective(bookkeeping);
    }

    public PageInfo<Bookkeeping> query(Bookkeeping bookkeeping) {
        if(bookkeeping != null && bookkeeping.getPage() != null){
            PageHelper.startPage(bookkeeping.getPage(),bookkeeping.getLimit());
        }
        return new PageInfo<Bookkeeping>(bookkeepingMapper.query(bookkeeping));
    }

    public Bookkeeping detail(Integer id) {
        return bookkeepingMapper.detail(id);
    }

    public int count(Bookkeeping bookkeeping) {
        return bookkeepingMapper.count(bookkeeping);
    }


    public PageInfo<Bookkeeping> queryMyDeviceExpenditure(Integer userId){
        return new PageInfo<Bookkeeping>(bookkeepingMapper.queryMyDeviceExpenditure(userId));
    }

    public List<Integer> queryMyDeviceEachClassificationAmount(Integer userId){
        return bookkeepingMapper.queryMyDeviceEachClassificationAmount(userId);
    }


    public Date queryMaxDate(Integer deviceId){
        return bookkeepingMapper.queryMaxDate(deviceId);
    }

    public double queryMonthlyExpenditureByClassification(Integer deviceId, Integer year, Integer month, Integer classification){
        return bookkeepingMapper.queryMonthlyExpenditureByClassification(deviceId, year, month, classification);
    }

    public PageInfo<Bookkeeping> queryMyDevice(Bookkeeping bookkeeping){
        if(bookkeeping != null && bookkeeping.getPage() != null){
            PageHelper.startPage(bookkeeping.getPage(),bookkeeping.getLimit());
        }
        return new PageInfo<Bookkeeping>(bookkeepingMapper.queryMyDevice(bookkeeping));
    }

}
