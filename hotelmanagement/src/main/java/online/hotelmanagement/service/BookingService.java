package online.hotelmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.hotelmanagement.constant.HotelManagementConstant;
import online.hotelmanagement.entity.ApplicationUser;
import online.hotelmanagement.entity.BookedRoom;
import online.hotelmanagement.entity.Booking;
import online.hotelmanagement.entity.PreClosure;
import online.hotelmanagement.entity.Room;
import online.hotelmanagement.entity.RoomType;
import online.hotelmanagement.repository.BookedRoomRepository;
import online.hotelmanagement.repository.BookingRepository;
import online.hotelmanagement.repository.RoomRepository;
import online.hotelmanagement.repository.RoomTypeRepository;

@Service
public class BookingService {
	@Autowired
	RoomRepository roomRepo;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	BookedRoomRepository bookedRoomRepo;
	@Autowired
	private RoomTypeRepository roomTypeRepo;

	public Booking saveBooking(Booking booking, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser user = (ApplicationUser) session
					.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			booking.setUserId(user.getUserId());
		} else {
			return null;
		}
		try {
			List<Room> allRooms = roomRepo.findByRoomTypeId(booking.getRoomTypeId());
			List<BookedRoom> bookedRooms = getBookedRooms(booking.getStartDate(), booking.getEndDate());
			for (BookedRoom bookedRoom : bookedRooms) {
				Iterator<Room> it = allRooms.iterator();
				while (it.hasNext()) {
					Room room = it.next();
					if (room.getRoomId() == bookedRoom.getRoomId()) {
						it.remove();
					}
				}
			}
			if (!allRooms.isEmpty() && allRooms.size() >= booking.getNoOfRooms()) {
				bookingRepo.save(booking);
				if (booking.getRooms() == null) {
					booking.setRooms(new ArrayList<Room>());
				}
				for (int i = 0; i < booking.getNoOfRooms(); i++) {
					BookedRoom bookedRoom = new BookedRoom();
					bookedRoom.setBookingId(booking.getBookingId());
					bookedRoom.setRoomId(allRooms.get(i).getRoomId());
					booking.getRooms().add(roomRepo.findOne(allRooms.get(i).getRoomId()));
					bookedRoomRepo.save(bookedRoom);
				}
				booking.setMessage("");
			} else {
				booking.setMessage("Room under selected type is not available");
			}
		} catch (Exception e) {
			if (booking.getBookingId() != null) {
				bookingRepo.delete(booking.getBookingId());
			}
			return null;
		}
		return booking;
	}

	public Booking getBookingDetails(Long bookingId) {
		Booking booking = bookingRepo.findByBookingId(bookingId);
		if(booking != null){
			List<BookedRoom> bookedRooms = bookedRoomRepo.findByBookingId(bookingId);
			for (BookedRoom bookedRoom : bookedRooms) {
				booking.getRooms().add(roomRepo.findByRoomId(bookedRoom.getRoomId()));
			}
		}
		return booking;
	}

	public List<Booking> getMyBookings(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Long userId;
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser user = (ApplicationUser) session
					.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			userId = user.getUserId();
			return bookingRepo.findByUserId(userId);
		}
		return null;
	}
	
	public List<Booking> getBookings(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser user = (ApplicationUser) session
					.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			if(user.getUserType() == 2){
				return bookingRepo.findByBookingStatus(1);
			}
		}
		return null;
	}
	
	public Booking cancelBooking(HttpServletRequest req, long bookingId) {
		return updateBookingStatus(req, bookingId, 3);
	}
	
	public Booking closeBooking(HttpServletRequest req, long bookingId) {
		return updateBookingStatus(req, bookingId, 2);
	}	
	
	public Booking bookingPreClosure(HttpServletRequest req, PreClosure preClosure) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser user = (ApplicationUser) session
					.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			Booking booking = bookingRepo.findByBookingId(preClosure.getBookingId());
			if(booking != null){
				if(user.getUserType() == 2){
					booking.setPreClosureDate(preClosure.getPreClosureDateTime());
					booking.setBookingStatus(4);
					bookingRepo.save(booking);
				}
				else{
					booking.setMessage("You are not authorized to perform this action. Please contact your administrator.");
				}
			}
			return booking;
		}
		return null;
	}
	
	private Booking updateBookingStatus(HttpServletRequest req, long bookingId, int bookingStatus) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser user = (ApplicationUser) session
					.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			Booking booking = bookingRepo.findByBookingId(bookingId);
			if(booking != null){
				if((user.getUserType() == 2) || (user.getUserType() == 1 && booking.getUserId() == user.getUserId())){
					booking.setBookingStatus(bookingStatus);
					bookingRepo.save(booking);
				}
				else{
					booking.setMessage("You are not authorized to perform this action. Please contact your administrator.");
				}
			}
			return booking;
		}
		return null;
	}
	
	private List<BookedRoom> getBookedRooms(LocalDate startDate, LocalDate endDate){
		List<RoomType> allRoomTypes = roomTypeRepo.findAll();
		Iterator<RoomType> it = allRoomTypes.iterator();
		List<BookedRoom> bookedRooms = new ArrayList<BookedRoom>();
		while (it.hasNext()) {
			RoomType roomType = it.next();
			List<Booking> allBookingsInRoomType = bookingRepo.findByBookingStatusAndRoomTypeId(1,
					roomType.getRoomTypeId());
			for (Booking booking : allBookingsInRoomType) {
				if (startDate.equals(booking.getStartDate()) || startDate.equals(booking.getEndDate())) {
					List<BookedRoom> rooms = bookedRoomRepo.findByBookingId(booking.getBookingId());
					if(rooms != null && !rooms.isEmpty()){
						bookedRooms.addAll(rooms);
					}
				} else if (startDate.isAfter(booking.getStartDate()) && startDate.isBefore(booking.getEndDate())) {
					List<BookedRoom> rooms = bookedRoomRepo.findByBookingId(booking.getBookingId());
					if(rooms != null && !rooms.isEmpty()){
						bookedRooms.addAll(rooms);
					}
				} else if (endDate.equals(booking.getStartDate()) || endDate.equals(booking.getEndDate())) {
					List<BookedRoom> rooms = bookedRoomRepo.findByBookingId(booking.getBookingId());
					if(rooms != null && !rooms.isEmpty()){
						bookedRooms.addAll(rooms);
					}
				} else if (endDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())) {
					List<BookedRoom> rooms = bookedRoomRepo.findByBookingId(booking.getBookingId());
					if(rooms != null && !rooms.isEmpty()){
						bookedRooms.addAll(rooms);
					}
				} else if (startDate.isBefore(booking.getStartDate()) && endDate.isAfter(booking.getEndDate())) {
					List<BookedRoom> rooms = bookedRoomRepo.findByBookingId(booking.getBookingId());
					if(rooms != null && !rooms.isEmpty()){
						bookedRooms.addAll(rooms);
					}
				}
			}
		}
		return bookedRooms;
	}
}
