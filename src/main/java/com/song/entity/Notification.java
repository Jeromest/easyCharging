package com.song.entity;

import org.hibernate.validator.constraints.Length;
import com.song.utils.Entity;
import java.util.Date;



public class Notification extends Entity{


	private Integer id;

	private Integer userId;

	@Length(max = 0)
	private String notiHead;

	@Length(max = 0)
	private String notiContent;


	private Integer notiRange;

	/**
	 * 通知类型
	 * 一般=0，重要=1，过期=2
	 */
	private Integer notiType;

	private Date notiDate;


	public User user;
	public Building building;
	public Device device;


	public Integer isMyNotification;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNotiHead() {
		return notiHead;
	}
	public void setNotiHead(String notiHead) {
		this.notiHead = notiHead;
	}
	public String getNotiContent() {
		return notiContent;
	}
	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}
	public Integer getNotiRange() {
		return notiRange;
	}
	public void setNotiRange(Integer notiRange) {
		this.notiRange = notiRange;
	}
	public Integer getNotiType() {
		return notiType;
	}
	public void setNotiType(Integer notiType) {
		this.notiType = notiType;
	}
	public Date getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Integer getIsMyNotification() {
		return isMyNotification;
	}

	public void setIsMyNotification(Integer isMyNotification) {
		this.isMyNotification = isMyNotification;
	}
}