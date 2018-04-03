package online.hotelmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RoomAmenity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomAmenityId;
	private Long roomTypeId;
	private String amenity;
	
	public RoomAmenity() {
		super();
	}

	public RoomAmenity(Long roomAmenityId, Long roomTypeId, String amenity) {
		super();
		this.roomAmenityId = roomAmenityId;
		this.roomTypeId = roomTypeId;
		this.amenity = amenity;
	}

	public Long getRoomAmenityId() {
		return roomAmenityId;
	}
	
	public void setRoomAmenityId(Long roomAmenityId) {
		this.roomAmenityId = roomAmenityId;
	}
	
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	
	public String getAmenity() {
		return amenity;
	}
	
	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}
}
