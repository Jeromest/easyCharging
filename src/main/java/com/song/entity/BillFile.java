package com.song.entity;

import org.hibernate.validator.constraints.Length;
import com.song.utils.Entity;
import java.util.Date;



public class BillFile extends Entity{


	private Integer id;
	@Length(max = 0)
	private String billTitle;
	private Date billDate;
	private Integer buildingId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillTitle() {
		return billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
}