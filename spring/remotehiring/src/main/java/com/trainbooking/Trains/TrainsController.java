package com.trainbooking.Trains;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TrainsController {
	
	@Autowired
	private TrainsService service;
	
	@GetMapping("/trains")
	public List<Trains> list(){
		return service.listAll();
	}

	@PostMapping("/trains/add")
	public boolean add(@RequestBody Trains train) {
		return service.addTrain(train);
	}
	
}
