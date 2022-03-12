package com.song.entity;

import com.song.utils.Entity;


public class Intention extends Entity{


	private Integer id;
	private String stuName;
	private Integer gender;


	private Integer late;

	private Integer noise;


	private Integer lateNNoise;


	private Integer maleBuilding;
	private Integer femaleBuilding;


	public Intention() {
	}

	public Intention(Integer id, String stuName, Integer gender, Integer late, Integer noise, Integer lateNNoise) {
		this.id = id;
		this.stuName = stuName;
		this.gender = gender;
		this.late = late;
		this.noise = noise;
		this.lateNNoise = lateNNoise;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getLate() {
		return late;
	}

	public void setLate(Integer late) {
		this.late = late;
	}

	public Integer getNoise() {
		return noise;
	}

	public void setNoise(Integer noise) {
		this.noise = noise;
	}

	public Integer getLateNNoise() {
		return lateNNoise;
	}

	public void setLateNNoise(Integer lateNNoise) {
		this.lateNNoise = lateNNoise;
	}

	public Integer getMaleBuilding() {
		return maleBuilding;
	}

	public void setMaleBuilding(Integer maleBuilding) {
		this.maleBuilding = maleBuilding;
	}

	public Integer getFemaleBuilding() {
		return femaleBuilding;
	}

	public void setFemaleBuilding(Integer femaleBuilding) {
		this.femaleBuilding = femaleBuilding;
	}
}