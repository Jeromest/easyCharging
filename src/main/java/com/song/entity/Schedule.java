package com.song.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.song.utils.Entity;
import java.util.Date;



public class Schedule extends Entity{


	private Integer id;
	private Integer userId;
	private String schName;
	private String schContent;

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date schTime;
	private String schLocation;
	private Integer schPriority;		// 日程优先级：普通=0；重要=1；极度重要=2

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

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public String getSchContent() {
		return schContent;
	}

	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}

	public Date getSchTime() {
		return schTime;
	}

	public void setSchTime(Date schTime) {
		this.schTime = schTime;
	}

	public String getSchLocation() {
		return schLocation;
	}

	public void setSchLocation(String schLocation) {
		this.schLocation = schLocation;
	}

	public Integer getSchPriority() {
		return schPriority;
	}

	public void setSchPriority(Integer schPriority) {
		this.schPriority = schPriority;
	}
}