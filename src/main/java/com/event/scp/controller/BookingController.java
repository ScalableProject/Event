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

// Controller for new booking and manage old booking

//http://scpeventaws-env.eba-fkdtam2z.us-east-1.elasticbeanstalk.com/

@RestController
@RequestMapping("/api/bookings")
//@CrossOrigin(origins="*/")
public class BookingController {

	// to overcome CORS issue
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
	
	//http://scpeventaws-env.eba-fkdtam2z.us-east-1.elasticbeanstalk.com/api/bookings/all
	//Getting all bookings
	@GetMapping("/all")
	public List <Booking> getAllBookings(){
		
		return this.bookingRepositery.findAll();
	}
	
	//https://ma7o43svol.execute-api.us-east-1.amazonaws.com/test/event-service?id=1
	//getting by id
	@GetMapping("/filter/{id}")
	public Booking getBookingById(@PathVariable (value = "id") long bookingId) {
		// if booking id not found
		return this.bookingRepositery.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id :" + bookingId));
	}
	
	// post booking
	// add booking to db
	@PostMapping
	public Booking createBooking(@RequestBody Booking booking) {
		
		return this.bookingRepositery.save(booking);
		
	}
	
	//put booking
	//edit existing booking
	@PutMapping("/{id}")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable ("id") long bookingId) {
	
		// if booking not found with specified id
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
	// delete specified id booking
	@DeleteMapping("/{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable ("id") long bookingId){
		
		Booking existing=this.bookingRepositery.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id :" + bookingId));
		
		this.bookingRepositery.delete(existing);
		
		return ResponseEntity.ok().build();
		
	}
	
}
