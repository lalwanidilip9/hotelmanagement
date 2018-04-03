package online.hotelmanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.hotelmanagement.constant.HotelManagementConstant;
import online.hotelmanagement.entity.ApplicationUser;
import online.hotelmanagement.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {
	@Autowired
	private ApplicationUserRepository repository;

	public ApplicationUser authenticateUser(String userName, String password, HttpServletRequest req) {
		List<ApplicationUser> allUsers = repository.findAll();
		HttpSession session = req.getSession();
		for (ApplicationUser applicationUser : allUsers) {
			if ((applicationUser.getEmailId().equalsIgnoreCase(userName)
					|| applicationUser.getMobileNumber().equalsIgnoreCase(userName))
							&& applicationUser.getPassword().equals(password) && applicationUser.isActive()) {
				session.setAttribute(HotelManagementConstant.APPLICATION_USER.name(), applicationUser);
				return applicationUser;
			}
		}
		return null;
	}

	public ApplicationUser saveUser(ApplicationUser user, HttpServletRequest req) {
		HttpSession session = req.getSession();
		ApplicationUser currentUser = null;
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			currentUser = (ApplicationUser) session.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			user.setUserId(currentUser.getUserId());
		}
		if(repository.findIfEmailIdAlreadyExists(user.getEmailId(), user.getUserId()) != null){
			user.setMessage("Email ID already exists");
			return user;
		}
		if(repository.findIfMobileAlreadyExists(user.getMobileNumber(), user.getUserId()) != null){
			user.setMessage("Mobile Number already exists");
			return user;
		}
		repository.save(user);
		session.removeAttribute(HotelManagementConstant.APPLICATION_USER.name());
		session.setAttribute(HotelManagementConstant.APPLICATION_USER.name(), user);
		return user;
	}

	public ApplicationUser saveUserProfile(ApplicationUser user) {
		return repository.save(user);
	}

	public ApplicationUser getUserDetails(Long userId) {
		return repository.findByUserId(userId);
	}

	public ApplicationUser getCurrentUserDetails(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			return (ApplicationUser) session.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
		}
		return null;
	}

	public ApplicationUser removeUser(HttpServletRequest req, Long userId) {
		HttpSession session = req.getSession();
		if (session.getAttribute(HotelManagementConstant.APPLICATION_USER.name()) != null) {
			ApplicationUser currentUser = (ApplicationUser) session.getAttribute(HotelManagementConstant.APPLICATION_USER.name());
			if((currentUser.getUserType() == 2) || (currentUser.getUserType() == 1 && currentUser.getUserId() == userId)){
				ApplicationUser applicationUser = repository.findByUserId(userId);
				applicationUser.setActive(false);
				repository.save(applicationUser);
				return applicationUser;
			}
			else{
				currentUser.setMessage("You are not authorized to perform this action. Please contact your administrator.");
				return currentUser;
			}
		}
		return null;
	}
}
