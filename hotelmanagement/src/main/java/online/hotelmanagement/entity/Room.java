package online.hotelmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomId;
	private String roomNumber;
	private Long roomTypeId;
	private int floorNumber;
	private String description;
	@Transient
	private String roomType;
	private String imagePath;

	public Room() {
		super();
	}

	public Room(Long roomId, String roomNumber, Long roomTypeId, int floorNumber, int roomCharge, String description,
			String imagePath) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomTypeId = roomTypeId;
		this.floorNumber = floorNumber;
		this.description = description;
		this.imagePath = "<img src='/img/rooms/" + imagePath + "' width='100' height='100' />";
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getImagePath() {
		if (!imagePath.startsWith("<img")) {
			imagePath = "<img src='/img/rooms/" + imagePath + "' width='100' height='100' />";
		}
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = "<img src='/img/rooms/" + imagePath + "' width='100' height='100' />";
	}
}
