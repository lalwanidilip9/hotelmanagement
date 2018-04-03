package online.hotelmanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home controller
 * @author Dilip
 *
 */

@Controller
public class GuestController extends BaseController {
	@Override
	protected String getHeaderTitle() {
		return "Opal - Home";
	}

	/**
	 * User home url
	 * @return User home url
	 */
	@RequestMapping("/")
	public String home() {
		return "user/rooms";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "guest/login";
	}
	
	@RequestMapping("/rooms")
	public String guestRooms() {
		return "user/rooms";
	}
	
	@RequestMapping("/events")
	public String guestEvents() {
		return "user/events";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUs() {
		return "guest/about";
	}
}
