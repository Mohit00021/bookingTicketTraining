package com.trainbooking.Bookings;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import com.trainbooking.Response.ResponseDto;
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
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Bookings add(Bookings booking) {
		Trains train = trainsRepository.findById(booking.getTrainid()).get();
		Bookings bookings = new Bookings();
		//Users user = this.usersRepository.findById(bookings.getEmail()).get();
		bookings.setNumberOfSeats(booking.getNumberOfSeats());
		bookings.setTrain_name(train.getTrain_name());
		bookings.setBookingTime(LocalTime.now());
			bookings.setArrivalDate(train.getArrival().toString());
			bookings.setDepartDate(booking.getDepartDate());
			bookings.setArrivalTime(train.getArrivalTime().toString());
			bookings.setDepartTime(train.getDepartureTime().toString());
			bookings.setSeat_type(booking.getSeat_type());
			bookings.setTrainFrom(booking.getTrainFrom());
			bookings.setTrainTo(booking.getTrainTo());
			bookings.setEmail(booking.getEmail());
			bookings.setTrainid(booking.getTrainid());
			bookings.setJurneyDistance(calculateDistance(booking));
			bookings.setPrice(seatservice.getSeatCost(booking.getSeat_type()) + (bookings.getJurneyDistance()*train.getPirceByKm()));
			bookings.setPrice(bookings.getPrice()*booking.getNumberOfSeats());
		System.out.println(bookings.getTrain_name()+"Ppointer");
			bookingsRepository.save(bookings);
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

	public double getTicketPrice(Bookings booking){
		System.out.println(booking.getTrainid());
		Trains trains = trainsRepository.findById(booking.getTrainid()).get();
		int distance = calculateDistance(booking);
		double price = (seatservice.getSeatCost(booking.getSeat_type()) + (distance*trains.getPirceByKm()))*booking.getNumberOfSeats();
		return price ;
	}
	public List<Bookings> listAll(){
		return bookingsRepository.findAll();
	}
	
	public List<Bookings> listByUsers(String email){
		return bookingsRepository.findAllByEmail(email);
	}
	
	public boolean endBooking(Integer bookingid) {

		Bookings bookings = this.bookingsRepository.findById(bookingid).get();
		bookings.setPaid(1);
		this.bookingsRepository.save(bookings);
		return true;

	}

}
