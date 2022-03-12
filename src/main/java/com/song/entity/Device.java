package com.song.entity;

import com.song.utils.Entity;


public class Device extends Entity{


	private Integer id;

	private Integer brand;

	private Integer buildingId;

	private Integer floor;

	private Integer deviceCapacity;

	private Integer deviceStatus;

	private Integer deviceType;

	private Double balance;



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getDeviceCapacity() {
		return deviceCapacity;
	}
	public void setDeviceCapacity(Integer deviceCapacity) {
		this.deviceCapacity = deviceCapacity;
	}
	public Integer getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}