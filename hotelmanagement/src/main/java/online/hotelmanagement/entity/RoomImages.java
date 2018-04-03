package online.hotelmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RoomImages {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomImageId;
	private Long roomTypeId;
	private String imagePath;
		
	public RoomImages() {
		super();
	}
	
	public RoomImages(Long roomImageId, Long roomTypeId, String imagePath) {
		super();
		this.roomImageId = roomImageId;
		this.roomTypeId = roomTypeId;
		this.imagePath = imagePath;
	}

	public Long getRoomImageId() {
		return roomImageId;
	}

	public void setRoomImageId(Long roomImageId) {
		this.roomImageId = roomImageId;
	}
	
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
