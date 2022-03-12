package com.song.entity;

import com.song.utils.Entity;


public class Building extends Entity{

	private Integer id;
	private String buildingName;		// 楼栋名
	private Integer floorNum;			// 楼层数
	private String address;				// 地址
	private Integer managerId;			// 管理员ID
	private String managerName;			// 管理员姓名

	/**
	 * @imp 关联到用户实体
	 * @silkTag 楼栋->根据网点表管理员ID，查用户表对应的经理名字
	 */
	private User user;


	private Integer freeStuBed;			// 剩余充电桩数量


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getFreeStuBed() {
		return freeStuBed;
	}

	public void setFreeStuBed(Integer freeStuBed) {
		this.freeStuBed = freeStuBed;
	}
}