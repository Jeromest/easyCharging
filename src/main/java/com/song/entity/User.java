package com.song.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.song.utils.Entity;

import java.util.List;



public class User extends Entity{

	private Integer id;
	private String userPwd;
	private String userName;
	private Integer gender;			// 用户性别：女=0；男=1
	private String email;
	private String tel;
	private Integer deviceId;
	private Integer userType;
	private List<Integer> billPaidInfo;
	private List<Double> billUsedAndFeeInfo;
	private Building building;
	private Device device;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore						// 返回Json时不显示密码
	public String getUserPwd() {
		return userPwd;
	}

	@JsonProperty					// 提交时能够使用
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public List<Integer> getBillPaidInfo() {
		return billPaidInfo;
	}

	public void setBillPaidInfo(List<Integer> billPaidInfo) {
		this.billPaidInfo = billPaidInfo;
	}

	public List<Double> getBillUsedAndFeeInfo() {
		return billUsedAndFeeInfo;
	}

	public void setBillUsedAndFeeInfo(List<Double> billUsedAndFeeInfo) {
		this.billUsedAndFeeInfo = billUsedAndFeeInfo;
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

	public User() {
	}

	public User(Integer id, String userPwd, String userName, Integer gender, String email, String tel, Integer deviceId, Integer userType) {
		this.id = id;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.tel = tel;
		this.deviceId = deviceId;
		this.userType = userType;
	}
}