package com.trainbooking.Trains;

import com.trainbooking.Routes.Route;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Table(name = "trains")
public class Trains {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trainid;
	private String train_name;
	private Integer slots = 0;
	private String area;
	private String cast;
	private String bannerimage;

	@Column(nullable=false)
	private String trainFrom;

	@Column
	private String trainTo;

	@Column
	private LocalDate departure;

	@Column
	private LocalDate arrival;

	@Column
	private LocalDate departureTime;

	@Column
	private LocalDate arrivalTime;

	@Column(name ="distanceKm" )
	private int distanceKm;

	@Column(name="CAPACITY")
	private int capacity;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "TRAIN_ROUTE_ID")
	private List<Route> route;

	@Column
	private double pirceByKm;
	
	
	public Trains() {
	}
}
