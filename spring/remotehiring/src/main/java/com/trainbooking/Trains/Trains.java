package com.trainbooking.Trains;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Trains {
	private Integer trainid;
	private String train_name;
	private Integer slots = 0;
	private String area;
	private String cast;
	private String bannerimage;
	
	
	public Trains() {
	}
	
	public Trains(Integer trainid, String train_name,Integer slots, String area,String cast,String bannerimage) {
		super();
		this.trainid = trainid;
		this.train_name = train_name;
		this.slots = slots;
		this.area = area;
		this.cast = cast;
		this.bannerimage = bannerimage;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getTrainid() {
		return trainid;
	}
	public void setTrainid(Integer trainid) {
		this.trainid = trainid;
	}
	public String getTrain_name() {
		return train_name;
	}
	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}
	public Integer getSlots() {
		return slots;
	}
	public void setSlots(Integer slots) {
		this.slots = slots;
	}
	public String getArea() {
		return area;
	}
	public void setBannerimage(String bannerimage) {
		this.bannerimage = bannerimage;
	}
	public String getBannerimage() {
		return bannerimage;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getCast() {
		return cast;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
