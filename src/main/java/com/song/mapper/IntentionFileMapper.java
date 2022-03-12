package com.song.mapper;

import java.util.List;

import com.song.entity.IntentionFile;


public interface IntentionFileMapper {

	public int create(IntentionFile intentionFile);

	public int delete(Integer id);

	public int update(IntentionFile intentionFile);

	public int updateSelective(IntentionFile intentionFile);

	public List<IntentionFile> query(IntentionFile intentionFile);

	public IntentionFile detail(Integer id);

	public int count(IntentionFile intentionFile);

}