package com.trainbooking.Seat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

	@Autowired
	private SeatRepository repo;

	public List<Seat> listAll(){
		return repo.findAll();
	}
	
	public Seat add(Seat seat){
		Seat vec =  repo.save(seat);
		return vec;
	}
	
	public int getSeatCost(String seat_type) {
		String cost = repo.getSeatCost(seat_type);
		
		return Integer.parseInt(cost);
	}
}
