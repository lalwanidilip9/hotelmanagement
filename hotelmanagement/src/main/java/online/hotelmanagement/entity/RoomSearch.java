package online.hotelmanagement.entity;

public class RoomSearch {
	private String startDate;
	private String endDate;
	private int noOfRooms;
	private int noOfAdults;
	private int noOfChildren;
	private Long roomTypeId;
	
	public RoomSearch() {
		super();
	}

	public RoomSearch(String startDate, String endDate, int noOfRooms, int noOfAdults, int noOfChildren,
			Long roomTypeId) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.noOfRooms = noOfRooms;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.roomTypeId = roomTypeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public int getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
}
