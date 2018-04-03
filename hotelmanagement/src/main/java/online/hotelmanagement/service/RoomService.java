package online.hotelmanagement.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.hotelmanagement.entity.Booking;
import online.hotelmanagement.entity.Room;
import online.hotelmanagement.repository.BookingRepository;
import online.hotelmanagement.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	public List<Room> getRooms(LocalDate startDate, LocalDate endDate){
		List<Room> allRooms = roomRepo.findAll();
		List<Booking> allBookings = bookingRepo.findByBookingStatus(1);
		for (Booking booking : allBookings) {
			Iterator<Room> it = allRooms.iterator();
			while (it.hasNext()) {
				Room room = it.next();
				/*if (room.getRoomId() == booking.getRoomId()) {
					if(startDate.equals(booking.getStartDate()) || startDate.equals(booking.getEndDate())){
						it.remove();
					}
					else if(startDate.isAfter(booking.getStartDate()) && startDate.isBefore(booking.getEndDate())){
						it.remove();
					}
					else if(endDate.equals(booking.getStartDate()) || endDate.equals(booking.getEndDate())){
						it.remove();
					}
					else if(endDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())){
						it.remove();
					}
				}*/
			}
		}
		return allRooms;
	}
}
