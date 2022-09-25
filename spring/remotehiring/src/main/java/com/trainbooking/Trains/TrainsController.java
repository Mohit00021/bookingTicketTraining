package com.trainbooking.Trains;

import java.util.List;

import com.trainbooking.Response.ResponseDto;
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
	private TrainsService trainsService;


	@GetMapping("/trains")
	public List<Trains> list(){
//		System.out.println(trainsService.listAll());
		return trainsService.listAll();

	}



	@PostMapping("/trains/add")
	public boolean add(@RequestBody Trains train) {
		return trainsService.addTrain(train);
	}

	@PostMapping("/api/create-train")
	//@ResponseBody
	public ResponseDto createTrain(@RequestBody List<TrainDto> trainDtoList) {
		return new ResponseDto().setMessage("Train Added" + this.trainsService.addNewTrain(trainDtoList));
	}
	@GetMapping("/api/get-all-trains")
	public ResponseDto getTrains(){
		return this.trainsService.getTrains();
	}

}
