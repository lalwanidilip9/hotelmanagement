package online.hotelmanagement.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import online.hotelmanagement.entity.ApplicationUser;
import online.hotelmanagement.service.ApplicationUserService;

@RestController
@RequestMapping(value = "/")
public class ApplicationUserController {
	@Autowired
	ApplicationUserService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/save")
	public ApplicationUser saveUser(@RequestBody ApplicationUser user, HttpServletRequest req) {
		return service.saveUser(user, req);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/delete")
	public ApplicationUser removeUser(HttpServletRequest req, @RequestBody Long userId) {
		return service.removeUser(req, userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/userprofile/save")
	public ApplicationUser saveUserProfile(@RequestBody ApplicationUser user) {
		return service.saveUserProfile(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/demouser")
	public String getDemoUser() {
		return "Dilip Lalwani";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/currentuser")
	public ApplicationUser getCurrentUserDetails(HttpServletRequest req) {
		return service.getCurrentUserDetails(req);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{uId}")
	public ApplicationUser getUserDetails(@PathVariable String uId) {
		Long userId;
		try{
			userId = Long.parseLong(uId);
		}
		catch (Exception e) {
			return null;
		}
		return service.getUserDetails(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/authenticate/{userName}/{password}")
	public ApplicationUser authenticateUser(@PathVariable String userName, @PathVariable String password, HttpServletRequest req) {
		return service.authenticateUser(userName, password, req);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/checkauthentication")
	public ApplicationUser checkIfUserIsLoggedIn(HttpServletRequest req) {
		return service.getCurrentUserDetails(req);
	}
}
