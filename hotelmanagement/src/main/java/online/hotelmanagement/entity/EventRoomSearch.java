package online.hotelmanagement.entity;

public class EventRoomSearch {
	private String startDate;
	private String endDate;
	private int noOfGuests;
	private int eventType;
	private String eventTypeName;
	private int mealOption;
	private String mealOptionName;
	
	public EventRoomSearch() {
		super();
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

	public int getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getMealOption() {
		return mealOption;
	}

	public void setMealOption(int mealOption) {
		this.mealOption = mealOption;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public String getMealOptionName() {
		return mealOptionName;
	}

	public void setMealOptionName(String mealOptionName) {
		this.mealOptionName = mealOptionName;
	}
}
