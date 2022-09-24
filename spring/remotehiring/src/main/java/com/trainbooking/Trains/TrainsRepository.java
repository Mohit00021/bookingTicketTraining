package com.trainbooking.Trains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TrainsRepository extends JpaRepository<Trains, Integer>{
	
	@Query("Select count(*) from Trains where train_name = ?1")
	Integer findCountOfTrain(String name);
	
	@Query("Select trainid from Trains where train_name = ?1")
	Integer getTrainId(String name);
	
	@Modifying
	@Transactional
	@Query("Update Trains loc set loc.slots = loc.slots+1 where loc.trainid = ?1")
	int updateSlot(Integer trainid);

	//@Query("Select train_name from Trains where trainid = ?1")
	//String getTrainName(Integer trainid);
}
