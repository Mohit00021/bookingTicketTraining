package com.trainbooking.Slots;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainbooking.Bookings.Bookings;
import com.trainbooking.Trains.TrainsRepository;
import com.trainbooking.Trains.TrainsService;


@Service
public class SlotsService {
	
	@Autowired
	private SlotsRepository repo;
	
	@Autowired
	private TrainsService trainservice;
	
	@Autowired
	private TrainsRepository trainrepo;
	
	public List<Slots> listAll(){
		return repo.findAll();
	}
	
	public boolean add(Slots slot) {
		if (trainrepo.existsById(slot.getTrainid())) {
			if (repo.existsById(slot.getSlotid())) {
				return false;
			}
			else {
				
				repo.save(slot);
				trainservice.addSlot(slot.getTrainid());
				return true;
			}
		}
		else {
			return false;
		}
		
	}
	
	public List<Slots> slotById(Integer trainid){
		return repo.slotById(trainid);
	}
	

	
	public void rollbackSlot(String slotid) {
		repo.rollbackSlot(slotid);
	}
}
