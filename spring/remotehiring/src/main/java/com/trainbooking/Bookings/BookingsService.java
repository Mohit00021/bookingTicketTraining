package com.trainbooking.Bookings;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import com.trainbooking.Routes.Route;
import com.trainbooking.Seat.Seat;
import com.trainbooking.Trains.Trains;
import com.trainbooking.Trains.TrainsRepository;
import com.trainbooking.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainbooking.Trains.TrainsService;
import com.trainbooking.Mail.MailService;
import com.trainbooking.Slots.SlotsService;
import com.trainbooking.Seat.SeatService;

@Service
public class BookingsService {
	
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private SlotsService slotservice;
	@Autowired
	private TrainsRepository trainsRepository;
	@Autowired
	private SeatService seatservice;
	
	private TrainsService trainservice;

	@Autowired
	private MailService mailservice;
	@Autowired
	private UsersRepository usersRepository;
	
	public Bookings add(Bookings booking) {
		Trains train = trainsRepository.findById(booking.getTrainid()).get();
		Bookings bookings = new Bookings();
		//Users user = this.usersRepository.findById(bookings.getEmail()).get();
		bookings.setEmail(bookings.getEmail());
		bookings.setBookingTime(LocalTime.now());
			//bookings.setPasengerNames(bookings.getPasengerNames());
			bookings.setArrivalDate(train.getArrival().toString());
			bookings.setDepartDate(booking.getDepartDate());
			bookings.setArrivalTime(train.getArrivalTime().toString());
			bookings.setDepartTime(booking.getDepartTime());
			bookings.setSeat_type(booking.getSeat_type());
			bookings.setTrainFrom(booking.getTrainFrom());
			bookings.setTrainTo(booking.getTrainTo());
			bookings.setEmail(booking.getEmail());
			bookings.setTrainid(booking.getTrainid());
			bookings.setJurneyDistance(calculateDistance(booking));
			bookings.setPrice(seatservice.getSeatCost(booking.getSeat_type()) + (bookings.getJurneyDistance()*train.getPirceByKm()));
			bookings.setPrice(bookings.getPrice()*booking.getNoOfSeats());
			this.bookingsRepository.save(bookings);
//			bookings.setBookings(bookings);
//			this.ticketRepository.save(bookings);

		return bookings;
	}

	private int calculateDistance(Bookings bookings) {
		int distance=0;
		Trains train = trainsRepository.findById(bookings.getTrainid()).get();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put(train.getTrainFrom(),1);
		map.put(train.getTrainTo(),train.getDistanceKm());
		if(!train.getRoute().isEmpty()){
			for (Route i : train.getRoute()){
				String key = i.getVia();
				Integer value = i.getDistanceFromSource();
				map.put(key,value);
			}
		}
		String d = bookings.getTrainTo();
		String s = bookings.getTrainFrom();
		int destination = map.get(d);
		int source = map.get(s);
		distance = (destination-source);
		System.out.println(destination-source);
		return (Math.abs(distance));
	}




		
	public List<Bookings> listAll(){
		return bookingsRepository.findAll();
	}
	
	public List<Bookings> listByUsers(String email){
		return bookingsRepository.listByUsers(email);
	}
	
	public boolean endBooking(Integer bookingid) {
		
		bookingsRepository.endBooking(bookingid);
		
		/*String[] time1 = getTime();
		String[] date1 = getDate();
		
		
		
		String bookingTime = repo.findById(bookingid).get().getTime();
		
		String bookingDate = repo.findById(bookingid).get().getDate();
		
		String[] date2 = splitDate(bookingDate);
		
		String[] time2 = splittime(bookingTime);
		
		//int duration = getDuration(time1,time2,date1,date2);*/


		Bookings currentBooking = bookingsRepository.findById(bookingid).get();

		int seatCost =  seatservice.getSeatCost(currentBooking.getSeat_type());

		

		
		bookingsRepository.save(currentBooking);
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
