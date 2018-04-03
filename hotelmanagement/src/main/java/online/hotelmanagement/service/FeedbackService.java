package online.hotelmanagement.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.hotelmanagement.constant.HotelManagementConstant;
import online.hotelmanagement.entity.ApplicationUser;
import online.hotelmanagement.entity.Feedback;
import online.hotelmanagement.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	FeedbackRepository repository;
	
	public Feedback saveFeedback(Feedback feedback, HttpServletRequest req) {
		HttpSession session = req.getSession();
		ApplicationUser currentUser = null;
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			currentUser = (ApplicationUser) session.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			feedback.setUserId(currentUser.getUserId());
			feedback.setUserName(currentUser.getUserName());
			feedback.setEmailID(currentUser.getEmailId());
			feedback.setMobileNumber(currentUser.getMobileNumber());
		}
		feedback.setFeedbackDate(LocalDate.now());
		repository.save(feedback);
		return feedback;
	}
	
	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbacks = repository.findAllOrderByFeedbackDateDesc();
		return feedbacks;
	}
}
