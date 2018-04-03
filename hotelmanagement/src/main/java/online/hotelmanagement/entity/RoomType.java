package online.hotelmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomTypeId;
	private String roomTypeName;
	private int noOfRooms;
	private int noOfMaxAdults;
	private int noOfMaxChildren;
	private int roomCharge;
	@Column(columnDefinition = "text")
	private String description;
	private String imagePath;

	public RoomType() {
		super();
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public int getNoOfMaxAdults() {
		return noOfMaxAdults;
	}

	public void setNoOfMaxAdults(int noOfMaxAdults) {
		this.noOfMaxAdults = noOfMaxAdults;
	}

	public int getNoOfMaxChildren() {
		return noOfMaxChildren;
	}

	public void setNoOfMaxChildren(int noOfMaxChildren) {
		this.noOfMaxChildren = noOfMaxChildren;
	}

	public int getRoomCharge() {
		return roomCharge;
	}

	public void setRoomCharge(int roomCharge) {
		this.roomCharge = roomCharge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
