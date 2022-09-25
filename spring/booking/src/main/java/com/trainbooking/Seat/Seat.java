package com.trainbooking.Seat;

import javax.persistence.*;


@Entity
@Table(name = "seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer seatid;
	@Column
	private String seat_type;
	@Column
	private String cost;
		
	public Seat() {
	}
	
	public Seat(Integer seatid, String seat_type, String cost) {
		super();
		this.seatid = seatid;
		this.seat_type = seat_type;
		this.cost = cost;
	}

	public Integer getSeatid() {
		return seatid;
	}
	public void setSeatid(Integer seatid) {
		this.seatid = seatid;
	}
	public String getSeat_type() {
		return seat_type;
	}
	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}	
	
	
	
}
