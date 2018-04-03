package online.hotelmanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin controller
 * @author Dilip
 *
 */

@Controller
public class AdminController extends BaseController {
	private static final String VIEW_PREFIX = "admin/bookings";

	@Override
	protected String getHeaderTitle() {
		return "Opal - Admin";
	}

	@RequestMapping("/admin")
	public String admin() {
		return VIEW_PREFIX;
	}
	
	@RequestMapping("/admin/bookings")
	public String adminBookings() {
		return VIEW_PREFIX;
	}
	
	@RequestMapping("/admin/bookingdetails")
	public String bookingDetails() {
		return "/admin/bookingdetails";
	}
	
	@RequestMapping("/admin/feedback")
	public String adminFeedback() {
		return "/admin/adminfeedback";
	}
	
	@RequestMapping("/admin/rooms")
	public String adminRoomSearch() {
		return "/user/rooms";
	}
	
	@RequestMapping("/admin/roommngt")
	public String adminRoomManagement() {
		return "/admin/roommanagement";
	}
	
	@RequestMapping("/admin/roomtypedetails")
	public String adminRoomTypeDetails() {
		return "/admin/roomtypedetails";
	}
}
