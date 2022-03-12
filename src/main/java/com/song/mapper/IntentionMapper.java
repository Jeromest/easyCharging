package com.song.mapper;

import java.util.List;

import com.song.entity.Intention;


public interface IntentionMapper {

	public int create(Intention intention);

	public int delete(Integer id);

	public int update(Intention intention);

	public int updateSelective(Intention intention);

	public List<Intention> query(Intention intention);

	public Intention detail(Integer id);

	public int count(Intention intention);

	// 将Excel数据添加到数据库
	int addIntentionExcelFileToDatabase(Intention intention);

	public int queryMaleAmount();

	public int queryFemaleAmount();

	public List<Integer> queryMaleIdByType();


}