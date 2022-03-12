package com.song.mapper;

import java.util.Date;
import java.util.List;

import com.song.entity.Bookkeeping;


public interface BookkeepingMapper {

	public int create(Bookkeeping bookkeeping);

	public int delete(Integer id);

	public int update(Bookkeeping bookkeeping);

	public int updateSelective(Bookkeeping bookkeeping);

	public List<Bookkeeping> query(Bookkeeping bookkeeping);

	public Bookkeeping detail(Integer id);

	public int count(Bookkeeping bookkeeping);

	public List<Bookkeeping> queryMyDeviceExpenditure(Integer userId);

	public List<Bookkeeping> queryMyDevice(Bookkeeping bookkeeping);

	public List<Integer> queryMyDeviceEachClassificationAmount(Integer userId);


	public Date queryMaxDate(Integer deviceId);

	public double queryMonthlyExpenditureByClassification(Integer deviceId, Integer year, Integer month, Integer classification);

}