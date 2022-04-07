package com.event.scp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.event.scp.entity.Booking;
import com.event.scp.exception.ResourceNotFoundException;
import com.event.scp.repository.BookingRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
//@CrossOrigin(origins="*/")
public class BookingController {

	
	@Bean
    public WebMvcConfigurer getCorsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*/")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
	
	@Autowired
	private BookingRepository bookingRepositery;
	
	//Getting all bookings
	@GetMapping("/all")
	public List <Booking> getAllBookings(){
		
		return this.bookingRepositery.findAll();
	}
	
	//getting by id
	@GetMapping("/filter/{id}")
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
		existing.setCity(booking.getCity());
		existing.setEircode(booking.getEircode());
		existing.setEmail(booking.getEmail());
		existing.setNumber(booking.getNumber());
		existing.setBooking_from(booking.getBooking_from());
		existing.setBooking_till(booking.getBooking_till());
		existing.setNo_people(booking.getNo_people());
		existing.setRequest(booking.getRequest());
		
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
