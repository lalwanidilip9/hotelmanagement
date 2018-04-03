package online.hotelmanagement.entity;

import java.time.LocalDate;

public class PreClosure {
	private String preClosureDate;
	private LocalDate preClosureDateTime;
	private Long bookingId;
	
	public PreClosure() {
		super();
	}
	
	public String getPreClosureDate() {
		return preClosureDate;
	}
	
	public void setPreClosureDate(String preClosureDate) {
		this.preClosureDate = preClosureDate;
	}
	
	public Long getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getPreClosureDateTime() {
		return preClosureDateTime;
	}

	public void setPreClosureDateTime(LocalDate preClosureDateTime) {
		this.preClosureDateTime = preClosureDateTime;
	}	
}
