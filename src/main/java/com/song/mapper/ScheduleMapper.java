package com.song.mapper;

import java.util.List;

import com.song.entity.Schedule;


public interface ScheduleMapper {

	public int create(Schedule schedule);

	public int delete(Integer id);

	public int update(Schedule schedule);

	public int updateSelective(Schedule schedule);

	public List<Schedule> query(Schedule schedule);

	public Schedule detail(Integer id);

	public int count(Schedule schedule);

	public int waitForCompletionAmount(String todayTime, Integer userId);

	public int alreadyFinishedAmount(String todayTime, Integer userId);

	public List<Schedule> querySchedule(Schedule schedule);
}