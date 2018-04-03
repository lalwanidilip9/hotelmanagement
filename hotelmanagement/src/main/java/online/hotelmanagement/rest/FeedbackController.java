package online.hotelmanagement.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import online.hotelmanagement.entity.Feedback;
import online.hotelmanagement.service.FeedbackService;

@RestController
@RequestMapping(value = "/")
public class FeedbackController {
	@Autowired
	FeedbackService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/feedback/save")
	public Feedback saveFeedback(@RequestBody Feedback feedback, HttpServletRequest req) {
		return service.saveFeedback(feedback, req);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/feedback/all")
	public List<Feedback> getAllFeedbacks() {
		return service.getAllFeedbacks();
	}
}
