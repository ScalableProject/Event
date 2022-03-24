package com.event.scp.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.event.scp.entity.Booking;
import com.event.scp.exception.ResourceNotFoundException;
import com.event.scp.repository.BookingRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingRepository bookingRepositery;
	
	//Getting all bookings
	@GetMapping
	public List <Booking> getAllBookings(){
		
		return this.bookingRepositery.findAll();
	}
	
	//getting by id
	@GetMapping("/{id}")
	public Booking getBookingById(@PathVariable (value = "id") long bookingId) {
		return this.bookingRepositery.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id :" + bookingId));
	}
	
	// post booking
	@PostMapping
	public Booking createBooking(@RequestBody Booking booking) {
		
		return this.bookingRepositery.save(booking);
		
	}
	
	//put booking
	@PutMapping("/{id}")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable ("id") long bookingId) {
	
		Booking existing=this.bookingRepositery.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id :" + bookingId));
		
		existing.setfName(booking.getfName());
		existing.setlName(booking.getlName());
		existing.setAddress(booking.getAddress());
		existing.setEmail(booking.getEmail());
		existing.setNumber(booking.getNumber());
		
		return this.bookingRepositery.save(existing);
		
	}
	
	//delete booking
	@DeleteMapping("/{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable ("id") long bookingId){
		
		Booking existing=this.bookingRepositery.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id :" + bookingId));
		
		this.bookingRepositery.delete(existing);
		
		return ResponseEntity.ok().build();
		
	}
	
}
