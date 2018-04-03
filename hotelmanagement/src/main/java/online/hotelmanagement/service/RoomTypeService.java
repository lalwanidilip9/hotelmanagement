package online.hotelmanagement.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import online.hotelmanagement.entity.Booking;
import online.hotelmanagement.entity.RoomAmenity;
import online.hotelmanagement.entity.RoomImages;
import online.hotelmanagement.entity.RoomType;
import online.hotelmanagement.repository.BookingRepository;
import online.hotelmanagement.repository.RoomAmenityRepository;
import online.hotelmanagement.repository.RoomImagesRepository;
import online.hotelmanagement.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	@Autowired
	private RoomTypeRepository roomTypeRepo;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private RoomImagesRepository roomImagesRepo;
	@Autowired
	private RoomAmenityRepository roomAmenityRepo;

	public void saveRoomType(RoomType roomType) {
		if (roomType.getRoomTypeId() != 0 && roomType.getRoomTypeId() != null) {
			RoomType rt = roomTypeRepo.findByRoomTypeId(roomType.getRoomTypeId());
			if (rt != null) {
				roomType.setImagePath(rt.getImagePath());
			}
		}
		roomTypeRepo.save(roomType);
	}

	public void deleteRoomTypeImage(Long imageId) {
		roomImagesRepo.delete(imageId);
	}

	public RoomType getRoomType(Long roomTypeId) {
		return roomTypeRepo.findByRoomTypeId(roomTypeId);
	}

	public List<RoomType> getAllRoomTypes() {
		return roomTypeRepo.findAll();
	}

	public List<RoomType> searchRoomTypes(LocalDate startDate, LocalDate endDate, int noOfAdults, int noOfChildren,
			int noOfRooms) {
		List<RoomType> allRoomTypes = roomTypeRepo.findAll();
		Iterator<RoomType> it = allRoomTypes.iterator();
		while (it.hasNext()) {
			RoomType roomType = it.next();
			int noOfRoomsInRoomType = roomType.getNoOfRooms();
			int noOfBookedRoomsInRoomType = 0;
			List<Booking> allBookingsInRoomType = bookingRepo.findByBookingStatusAndRoomTypeId(1,
					roomType.getRoomTypeId());
			for (Booking booking : allBookingsInRoomType) {
				if (startDate.equals(booking.getStartDate()) || startDate.equals(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (startDate.isAfter(booking.getStartDate()) && startDate.isBefore(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (endDate.equals(booking.getStartDate()) || endDate.equals(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (endDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (startDate.isBefore(booking.getStartDate()) && endDate.isAfter(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				}
			}
			if (noOfRoomsInRoomType - noOfBookedRoomsInRoomType < noOfRooms) {
				it.remove();
			}
		}
		return allRoomTypes;
	}

	public List<RoomImages> getRoomImages(Long roomTypeId) {
		return roomImagesRepo.findRoomImagesByRoomTypeId(roomTypeId);
	}

	public List<RoomAmenity> getRoomTypeAmenities(Long roomTypeId) {
		return roomAmenityRepo.findAmenityByRoomTypeId(roomTypeId);
	}

	public List<RoomImages> getRoomImagesForPopup(Long roomTypeId) {
		return roomImagesRepo.findRoomImagesByRoomTypeId(roomTypeId);
	}
	
	public List<RoomType> searchEventHalls(LocalDate startDate, LocalDate endDate, int noOfGuests) {
		List<RoomType> allHalls = roomTypeRepo.findByEventRoomTypeId(noOfGuests);
		Iterator<RoomType> it = allHalls.iterator();
		while (it.hasNext()) {
			RoomType roomType = it.next();
			int noOfRoomsInRoomType = roomType.getNoOfRooms();
			int noOfBookedRoomsInRoomType = 0;
			List<Booking> allBookingsInRoomType = bookingRepo.findByBookingStatusAndRoomTypeId(1,
					roomType.getRoomTypeId());
			for (Booking booking : allBookingsInRoomType) {
				if (startDate.equals(booking.getStartDate()) || startDate.equals(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (startDate.isAfter(booking.getStartDate()) && startDate.isBefore(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (endDate.equals(booking.getStartDate()) || endDate.equals(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (endDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				} else if (startDate.isBefore(booking.getStartDate()) && endDate.isAfter(booking.getEndDate())) {
					noOfBookedRoomsInRoomType += booking.getNoOfRooms();
				}
			}
			if (noOfRoomsInRoomType - noOfBookedRoomsInRoomType < 1) {
				it.remove();
			}
		}
		return allHalls;
	}
}
