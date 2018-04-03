package online.hotelmanagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;
	private Long roomTypeId;
	private Long userId;
	private String guestName;
	private String emailID;
	private String mobileNumber;
	private String address;
	private String city;
	private String state;
	private String pinCode;
	private int noOfRooms;
	private int noOfAdults;
	private int noOfChildren;
	private int bookingStatus;
	@Transient
	private String bookingStatusName;
	private int paymentModeId;
	@Transient
	private String paymentMode;
	private String cardNumber;
	private int cardExpMonth;
	private int cardExpYear;
	@Transient
	private String startDateString;
	@Transient
	private String endDateString;
	@Transient
	private BookedRoom bookedRoom;
	@Transient
	private String message;
	@Transient
	private List<Room> rooms;
	
	private int eventType;
	@Transient
	private String eventTypeName;
	private int mealOption;
	@Transient
	private String mealOptionName;

	@Column(name = "startDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;

	@Column(name = "endDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;

	@Column(name = "preClosureDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate preClosureDate;

	public Booking() {
		super();		
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getBookingStatus() {
		setBookingStatusName(bookingStatus);
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
		setBookingStatusName(bookingStatus);
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

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(int cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public int getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(int cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public BookedRoom getBookedRoom() {
		return bookedRoom;
	}

	public void setBookedRoom(BookedRoom bookedRoom) {
		this.bookedRoom = bookedRoom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Room> getRooms() {
		if (rooms == null) {
			rooms = new ArrayList<>();
		}
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public LocalDate getPreClosureDate() {
		return preClosureDate;
	}

	public void setPreClosureDate(LocalDate preClosureDate) {
		this.preClosureDate = preClosureDate;
	}

	public String getBookingStatusName() {
		return this.bookingStatusName;
	}

	public void setBookingStatusName(String bookingStatusName) {
		this.bookingStatusName = bookingStatusName;
	}
	
	private void setBookingStatusName(int bookingStatus){
		switch (bookingStatus) {
		case 1:
			this.bookingStatusName = "Confirmed";
			break;
		case 2:
			this.bookingStatusName = "Closed";
			break;
		case 3:
			this.bookingStatusName = "Cancelled";
			break;
		case 4:
			this.bookingStatusName = "Preclosure";
			break;
		default:
			this.bookingStatusName = "Confirmed";
			break;
		}
	}
	
	private void setEventTypeName(int eventType){
		switch (eventType) {
		case 1:
			this.eventTypeName = "Birthday";
			break;
		case 2:
			this.eventTypeName = "Business Meeting";
			break;
		case 3:
			this.eventTypeName = "Casual Meeting/Party";
			break;
		case 4:
			this.eventTypeName = "Engagement";
			break;
		case 5:
			this.eventTypeName = "Family Function";
			break;
		default:
			this.eventTypeName = "Wedding";
			break;
		}
	}
	
	private void setMealOptionName(int mealOption){
		switch (mealOption) {
		case 1:
			this.mealOptionName = "Breakfast";
			break;
		case 2:
			this.mealOptionName = "Lunch";
			break;
		case 3:
			this.mealOptionName = "Evening Snacks";
			break;
		case 4:
			this.mealOptionName = "Dinner";
			break;
		default:
			this.mealOptionName = "Dinner";
			break;
		}
	}

	public int getEventType() {
		setEventTypeName(eventType);
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
		setEventTypeName(eventType);
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public int getMealOption() {
		setMealOptionName(mealOption);
		return mealOption;
	}

	public void setMealOption(int mealOption) {
		this.mealOption = mealOption;
		setMealOptionName(mealOption);
	}

	public String getMealOptionName() {
		return mealOptionName;
	}

	public void setMealOptionName(String mealOptionName) {
		this.mealOptionName = mealOptionName;
	}
}
