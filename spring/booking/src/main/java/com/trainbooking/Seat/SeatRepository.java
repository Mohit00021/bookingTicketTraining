package com.trainbooking.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

	@Query("Select cost from Seat where seat_type = ?1")
	String getSeatCost(String seat_type);
	
}
