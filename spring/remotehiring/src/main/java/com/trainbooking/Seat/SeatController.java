package com.trainbooking.Seat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SeatController {
	@Autowired
	private SeatService service;
	
	@GetMapping("/seat")
	public List<Seat> list(){
		return service.listAll();
	}
	
	@PostMapping("/seat/add")
	public ResponseEntity<Seat> add(@RequestBody Seat seat){
		return new ResponseEntity<Seat>(service.add(seat),HttpStatus.OK);
	}
}
