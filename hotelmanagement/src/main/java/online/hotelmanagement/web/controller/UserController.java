package online.hotelmanagement.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import online.hotelmanagement.constant.HotelManagementConstant;

/**
 * User controller
 * @author Dilip
 *
 */

@Controller
public class UserController extends BaseController {
	private static final String VIEW_PREFIX = "user/rooms";

	@Override
	protected String getHeaderTitle() {
		return "Hotel Management - Home";
	}
	
	/**
	 * User home url
	 * @return User home url
	 */
	@RequestMapping("/user")
	public String userHome() {
		return VIEW_PREFIX;
	}
	
	/**
	 * User home url
	 * @return User home url
	 */
	@RequestMapping("/user/rooms")
	public String userRoomsHome() {
		return VIEW_PREFIX;
	}
	
	@RequestMapping("/user/events")
	public String userEvents() {
		return "/user/events";
	}
	
	@RequestMapping("/user/eventhalls")
	public String userEventRooms() {
		return "/user/eventhalls";
	}
	
	@RequestMapping("/user/eventbooking")
	public String userEventBooking() {
		return "/user/eventbooking";
	}
	
	@RequestMapping("/user/rooms/details")
	public String userRoomDetails(@RequestParam String roomId) {
		return "user/rd";
	}
	
	@RequestMapping("/user/roomtypes")
	public String roomTypes() {
		return "user/roomtypes";
	}
	
	@RequestMapping("/user/booking")
	public String booking() {
		return "user/booking";
	}
	
	@RequestMapping("/user/bookingconfirmation")
	public String bookingconfirmation() {
		return "user/bookingconfirmation";
	}
	
	@RequestMapping("/user/eventbookingconfirmation")
	public String eventbookingconfirmation() {
		return "user/eventbookingconfirmation";
	}
	
	@RequestMapping("/user/profile")
	public String userProfile() {
		return "user/userprofile";
	}
	
	@RequestMapping("/feedback")
	public String userFeedback() {
		return "user/feedback";
	}
	
	@RequestMapping("/user/mybookings")
	public String userBookings() {
		return "user/mybookings";
	}

	/**
	 * Redirect to logout.
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute(HotelManagementConstant.APPLICATION_USER.name());
		request.logout();
		return "redirect:/login";
	}
}
