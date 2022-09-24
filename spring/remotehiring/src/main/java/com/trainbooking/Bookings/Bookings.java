package com.trainbooking.Bookings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Bookings {
	private Integer bookingid;
	private String email;
	private String seat_type;
	private String  seat_no;
	private String date;
	private String time;
	private Integer duration;
	private String cost = "0";
	private Integer trainid;
	private String slotid;
	private Integer paid = 0;
	private String train_name = "";
	
	public Bookings() {

	}
	public Bookings(Integer bookingid, String email, String seat_type, String seat_no, String date, String time,
			Integer duration, String cost, Integer trainid, String slotid, Integer paid,String train_name) {
		super();
		this.bookingid = bookingid;
		this.email = email;
		this.seat_type = seat_type;
		this.seat_no = seat_no;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.cost = cost;
		this.trainid = trainid;
		this.slotid = slotid;
		this.paid = paid;
		this.train_name = train_name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBookingid() {
		return bookingid;
	}
	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public String getSlotno() {
		return slotno;
	}
	public void setSlotno(String slotno) {
		this.slotno = slotno;
	}*/
	public String getTrain_name() {
		return train_name;
	}
	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}

	public String getSeat_type() {
		return seat_type;
	}
	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	public String getSeat_no() {
		return seat_no;
	}
	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Integer getTrainid() {
		return trainid;
	}
	public void setTrainid(Integer trainid) {
		this.trainid = trainid;
	}
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	public String getSlotid() {
		return slotid;
	}
	public void setSlotid(String slotid) {
		this.slotid = slotid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
}
