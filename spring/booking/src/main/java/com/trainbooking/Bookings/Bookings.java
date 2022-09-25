package com.trainbooking.Bookings;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column
	private Integer bookingid;
	@Column
	private String email;
	@Column
	private String seat_type;
	@Column
	private Integer trainid;
//	@Column
//	private String slotid;
	@Column
	private Integer paid = 0;
	@Column
	private String train_name = "";

	@Column
	private LocalTime bookingTime;


	@Column (name="PRICE")
	private double price;

	@Column
	private int numberOfSeats;

	@Column(name="DEPART_DATE", nullable=false)
	private String departDate;

	@Column(name="DEPART_TIME", nullable=false)
	private String departTime;

	@Column(name="ARRIVAL_DATE", nullable=false)
	private String arrivalDate;

	@Column(name="ARRIVAL_TIME", nullable=false)
	private String arrivalTime;

	@Column(length = 40,nullable=false)
	private String trainFrom;

	@Column(length = 40, nullable=false)
	private String trainTo;

	@Column(nullable = false)
	private int jurneyDistance;
	
	public Bookings() {

	}
}
