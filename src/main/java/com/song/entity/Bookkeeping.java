package com.song.entity;

import org.hibernate.validator.constraints.Length;
import com.song.utils.Entity;
import java.util.Date;



public class Bookkeeping extends Entity{


	private Integer id;
	private Integer deviceId;
	private Double balance;
	private Integer userId;
	private Double bkMoney;
	private Integer bkType;			// 账目的类型：支出=0；收入=1


	private Integer classification;

	@Length(max = 0)
	private String remark;
	private Date bkTime;



	private User user;
	private Device device;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getBkMoney() {
		return bkMoney;
	}

	public void setBkMoney(Double bkMoney) {
		this.bkMoney = bkMoney;
	}

	public Integer getBkType() {
		return bkType;
	}

	public void setBkType(Integer bkType) {
		this.bkType = bkType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBkTime() {
		return bkTime;
	}

	public void setBkTime(Date bkTime) {
		this.bkTime = bkTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getClassification() {
		return classification;
	}

	public void setClassification(Integer classification) {
		this.classification = classification;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
}