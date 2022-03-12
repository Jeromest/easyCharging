package com.song.mapper;

import java.util.List;

import com.song.entity.Notification;


public interface NotificationMapper {

	public int create(Notification notification);

	public int delete(Integer id);

	public int update(Notification notification);

	public int updateSelective(Notification notification);

	public List<Notification> query(Notification notification);

	public Notification detail(Integer id);

	public int count(Notification notification);

	public List<Notification> queryMyBuilding(Integer buildingId);

	public List<Notification> queryMyDevice(Integer userId);

}