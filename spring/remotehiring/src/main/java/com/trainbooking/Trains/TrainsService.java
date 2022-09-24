package com.trainbooking.Trains;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainsService {
	
	@Autowired
	private TrainsRepository repo;
	
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
	/*public String getTrain_name(Integer locid){
		String loc = repo.getTrainName(locid);
		return loc;
	}*/
}
