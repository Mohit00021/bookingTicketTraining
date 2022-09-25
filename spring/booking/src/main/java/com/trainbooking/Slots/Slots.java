package com.trainbooking.Slots;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Slots {
	@Column
	@Id private String slotid;
	@Column
	private Integer trainid;
	@Column
	private String slotno;
	@Column
	private Integer status = 0;
	@Column
	private String time = "0";
	@Column
	private Integer duration = 0;

	
	public Slots() {
	}
	
	public Slots(String slotid, Integer trainid, String slotno, Integer status, String time, Integer duration) {
		super();
		this.slotid = slotid;
		this.trainid = trainid;
		this.slotno = slotno;
		this.status = status;
		this.time = time;
		this.duration = duration;
	}
	
	public String getSlotid() {
		return slotid;
	}
	
	public void setSlotid(String slotid) {
		this.slotid = slotid;
	}
	public Integer getTrainid() {
		return trainid;
	}
	public void setTrainid(Integer trainid) {
		this.trainid = trainid;
	}
	public String getSlotno() {
		return slotno;
	}
	public void setSlotno(String slotno) {
		this.slotno = slotno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
}
