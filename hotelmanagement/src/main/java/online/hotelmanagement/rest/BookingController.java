package online.hotelmanagement.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import online.hotelmanagement.entity.Booking;
import online.hotelmanagement.entity.PreClosure;
import online.hotelmanagement.service.BookingService;

@RestController
@RequestMapping(value = "/")
public class BookingController {
	@Autowired
	BookingService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/booking/save")
	public Booking saveBooking(@RequestBody Booking booking, HttpServletRequest req) {
		booking.setStartDate(LocalDate.parse(booking.getStartDateString(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		booking.setEndDate(LocalDate.parse(booking.getEndDateString(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		if(booking.getBookingId() == null){
			booking.setBookingStatus(1);
		}
		return service.saveBooking(booking, req);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/booking/{bId}")
	public Booking getBookingDetails(@PathVariable String bId) {
		Long bookingId;
		try{
			bookingId = Long.parseLong(bId);
		}
		catch (Exception e) {
			return null;
		}
		return service.getBookingDetails(bookingId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/booking/mybookings")
	public List<Booking> getMyBookings(HttpServletRequest req) {
		return service.getMyBookings(req);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/booking/bookings")
	public List<Booking> getBookings(HttpServletRequest req) {
		return service.getBookings(req);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/booking/cancel")
	public Booking cancelBooking(@RequestBody String bId, HttpServletRequest req) {
		Long bookingId;
		try{
			bookingId = Long.parseLong(bId);
		}
		catch (Exception e) {
			return null;
		}
		return service.cancelBooking(req, bookingId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/booking/close")
	public Booking closeBooking(@RequestBody String bId, HttpServletRequest req) {
		Long bookingId;
		try{
			bookingId = Long.parseLong(bId);
		}
		catch (Exception e) {
			return null;
		}
		return service.closeBooking(req, bookingId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/booking/preclosure")
	public Booking bookingPreClosure(@RequestBody PreClosure preClosure, HttpServletRequest req) {
		preClosure.setPreClosureDateTime(LocalDate.parse(preClosure.getPreClosureDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		return service.bookingPreClosure(req, preClosure);
	}
}
