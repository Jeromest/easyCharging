package com.song.entity;

import org.hibernate.validator.constraints.Length;
import com.song.utils.Entity;
import java.util.Date;



public class IntentionFile extends Entity{


	private Integer id;

	@Length(max = 0)
	private String fileName;

	private Date uploadTime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}