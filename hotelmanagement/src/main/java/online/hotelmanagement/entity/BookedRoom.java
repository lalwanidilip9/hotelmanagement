package online.hotelmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BookedRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookedRoomId;
	private Long roomId;
	private Long bookingId;
	
	public BookedRoom() {
		super();
	}
	
	public BookedRoom(Long bookedRoomId, Long roomId, Long bookingId) {
		super();
		this.bookedRoomId = bookedRoomId;
		this.roomId = roomId;
		this.bookingId = bookingId;
	}
	
	public Long getBookedRoomId() {
		return bookedRoomId;
	}
	
	public void setBookedRoomId(Long bookedRoomId) {
		this.bookedRoomId = bookedRoomId;
	}
	
	public Long getRoomId() {
		return roomId;
	}
	
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	public Long getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
}
