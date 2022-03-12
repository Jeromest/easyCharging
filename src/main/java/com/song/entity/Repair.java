package com.song.entity;

import com.song.utils.Entity;
import java.util.Date;



public class Repair extends Entity{

	private Integer id;

	private String repItem;

	private String description;

	private Date repDate;

	private Integer stuId;

	private Integer buildingId;

	private Integer deviceId;

	private Integer repStatus;		// 维修状态：等待维修=0；维修完成=1

	private String repMan;


	/**
	 * 引入User、Building、Device三个实体
	 */
	private User user;
	private Building building;
	private Device device;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getRepItem() {
		return repItem;
	}

	public void setRepItem(String repItem) {
		this.repItem = repItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRepDate() {
		return repDate;
	}

	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(Integer repStatus) {
		this.repStatus = repStatus;
	}

	public String getRepMan() {
		return repMan;
	}

	public void setRepMan(String repMan) {
		this.repMan = repMan;
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
}