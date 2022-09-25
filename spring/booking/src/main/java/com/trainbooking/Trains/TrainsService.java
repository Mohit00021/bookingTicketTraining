package com.trainbooking.Trains;

import java.util.List;

import com.trainbooking.Response.ResponseDto;
import com.trainbooking.Routes.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainsService {
	
	@Autowired
	private TrainsRepository repo;
	private RouteService routService;

	private final TrainsRepository trainsRepository;

	public TrainsService(TrainsRepository repo, RouteService routService, TrainsRepository trainsRepository) {
		this.repo = repo;
		this.routService = routService;
		this.trainsRepository = trainsRepository;
	}

	public List<Trains> listAll(){
		return repo.findAll();
	}
	public boolean addTrain(Trains train) {	
		Integer count = repo.findCountOfTrain(train.getTrain_name());
		if (count>0) {
			return false;
		}
		else {
			repo.save(train);
			return true;			
		}	
	}
	public void addSlot(Integer locid) {
		repo.updateSlot(locid);
	}
	public ResponseDto addNewTrain(List<TrainDto> trainDtoList){

		for(TrainDto trainDetails : trainDtoList){
			Trains train = new Trains();
			train.setTrain_name(trainDetails.getTrainName());
			train.setTrainFrom(trainDetails.getTrainFrom());
			train.setTrainTo(trainDetails.getTrainTo());
			train.setDeparture(trainDetails.getDeparture());
			train.setArrival(trainDetails.getArrival());
			train.setDepartureTime(trainDetails.getDepartureTime());
			train.setArrivalTime(trainDetails.getArrivalTime());
			train.setCapacity(trainDetails.getCapacity());
			train.setRoute(this.routService.saveTrainRoutes(trainDetails.getRouteDto()));
			this.trainsRepository.save(train);
		}
		return new ResponseDto().setMessage("Train Added");
	}

	public ResponseDto updateTrain(Integer trainId,TrainDto trainDetails){
		if(trainsRepository.findById(trainId).isPresent()){
			Trains existingTrain = trainsRepository.findById(trainId).get();
			existingTrain.setTrainFrom(trainDetails.getTrainFrom());
			existingTrain.setTrainTo(trainDetails.getTrainTo());
			existingTrain.setCapacity(trainDetails.getCapacity());
			existingTrain.setDeparture(trainDetails.getDeparture());
			existingTrain.setArrival(trainDetails.getArrival());
			existingTrain.setDepartureTime(trainDetails.getDepartureTime());
			existingTrain.setArrivalTime(trainDetails.getArrivalTime());
			existingTrain.setCapacity(trainDetails.getCapacity());
			this.trainsRepository.save(existingTrain);
			return new ResponseDto().setMessage("Train Details Updated");
		}else {
			return new ResponseDto().setMessage("No such train found");
		}
	}

	public ResponseDto getTrains(){
		List<Trains> trainList = this.trainsRepository.findAll();
		return new ResponseDto().setData(trainList);
	}
	/*public String getTrain_name(Integer locid){
		String loc = repo.getTrainName(locid);
		return loc;
	}*/
}
