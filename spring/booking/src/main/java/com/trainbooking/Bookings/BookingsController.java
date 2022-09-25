package com.trainbooking.Bookings;

import java.util.List;

import com.trainbooking.Response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingsController {

	@Autowired
	private BookingsService service;

	@PostMapping("/bookings/add")
	public Bookings add(@RequestBody Bookings booking) {
		return service.add(booking);
	}

	@GetMapping("/bookings")
	public List<Bookings> listAll() {
		return service.listAll();
	}

	@GetMapping("bookings/getByUser/{email}")
	public List<Bookings> listByUsers(@PathVariable String email) {
		return service.listByUsers(email);
	}

	@GetMapping("/bookings/endBooking/{bookingid}")
	public boolean endBooking(@PathVariable Integer bookingid) {
		return service.endBooking(bookingid);
	}

	@GetMapping("/bookings/allBookings")
	public List<Bookings> allBookings() {
		return service.listAll();
	}

	@PostMapping("bookings/price")
	public double getTotal(@RequestBody Bookings bookings){
		System.out.println(bookings.getTrainid() + bookings.getTrainFrom());
		return service.getTicketPrice(bookings);
	}
}
