package com.song.mapper;

import java.util.List;

import com.song.entity.BillFile;


public interface BillFileMapper {

	public int create(BillFile billFile);

	public int delete(Integer id);

	// 全字段更新
	public int update(BillFile billFile);

	// 部分字段更新
	public int updateSelective(BillFile billFile);

	public List<BillFile> query(BillFile billFile);

	public BillFile detail(Integer id);

	public int count(BillFile billFile);



}