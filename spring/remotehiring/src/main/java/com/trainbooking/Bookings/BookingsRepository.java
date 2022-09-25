package com.trainbooking.Bookings;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface BookingsRepository extends JpaRepository<Bookings, Integer>{
@Query("select b from Bookings b where b.email = ?1 and b.jurneyDistance<> null")
	List<Bookings> findAllByEmail(String email);

	@Query("Select b from Bookings b where email = ?1")
	List<Bookings> getByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("Update Bookings b set b.paid = 1 where b.bookingid = ?1")
	int endBooking(Integer bookingid);

}
