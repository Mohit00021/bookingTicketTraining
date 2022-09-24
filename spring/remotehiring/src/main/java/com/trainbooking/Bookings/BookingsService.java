package com.trainbooking.Bookings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainbooking.Trains.TrainsService;
import com.trainbooking.Mail.MailService;
import com.trainbooking.Slots.SlotsService;
import com.trainbooking.Seat.SeatService;

@Service
public class BookingsService {
	
	@Autowired
	private BookingsRepository repo;
	@Autowired
	private SlotsService slotservice;
	
	@Autowired
	private SeatService seatservice;
	
	private TrainsService trainservice;

	@Autowired
	private MailService mailservice;
	
	public Bookings add(Bookings booking) {
		
		Bookings currentBooking =  repo.save(booking);	
//		mailservice.sendEmail(currentBooking);
		slotservice.updateSlot(booking);

		//int duration = repo.findById(currentBooking.bookingid).get().getDuration();

		int duration = currentBooking.getDuration();
		//Bookings currBooking = repo.findById(currentBooking.bookingid).get();
		Bookings currBooking = currentBooking;
		currBooking.setDuration(duration);
		
		int seatCost =  seatservice.getSeatCost(currBooking.getSeat_type());

		//String trainname = trainservice.getTrain_name(currBooking.getTrainid());
		
		int cost = duration*seatCost;
		
		currBooking.setCost(String.valueOf(cost));

		//currBooking.setTrain_name(trainname);

		repo.save(currBooking);

		return currentBooking;
	}
		
	public List<Bookings> listAll(){
		return repo.findAll();
	}
	
	public List<Bookings> listByUsers(String email){
		return repo.listByUsers(email);
	}
	
	public boolean endBooking(Integer bookingid) {
		
		repo.endBooking(bookingid);
		
		/*String[] time1 = getTime();
		String[] date1 = getDate();
		
		
		
		String bookingTime = repo.findById(bookingid).get().getTime();
		
		String bookingDate = repo.findById(bookingid).get().getDate();
		
		String[] date2 = splitDate(bookingDate);
		
		String[] time2 = splittime(bookingTime);
		
		//int duration = getDuration(time1,time2,date1,date2);*/
		
		int duration = repo.findById(bookingid).get().getDuration();

		Bookings currentBooking = repo.findById(bookingid).get();
		
		currentBooking.setDuration(duration);
		
		int seatCost =  seatservice.getSeatCost(currentBooking.getSeat_type());
		
		int cost = duration*seatCost;
		
		currentBooking.setCost(String.valueOf(cost));
		
		repo.save(currentBooking);
		slotservice.rollbackSlot(currentBooking.getSlotid());
		
		return true;
	}
	
	public String[] getDate() {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        String time = sd.format(date);
        String[] datearr = time.split("-");
        return (datearr);
	}
	
	public String[] getTime() {
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        String time = sd.format(date);
        String[] timearr = time.split(":");
        return (timearr);
	}
	
	public String[] splitDate(String date) {
		return date.split("-");
	}
	
	public String[] splittime(String time) {
		return  time.split(":");
	}
	
	public int getDuration(String[] time1,String[] time2, String[] date1, String[] date2) {
		//int d1 = Integer.parseInt(date1[0]);
		//int d2 = Integer.parseInt(date2[0]);
		//int t1 = Integer.parseInt(time1[0]);
		//int t2 = Integer.parseInt(time2[0]);
		//int time = Math.abs(d1-d2) * 24;
		
		//return Math.abs(t1-t2) + time;
		return 10;
	}
}
