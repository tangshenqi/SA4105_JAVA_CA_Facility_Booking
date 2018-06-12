package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usrService;

	@GetMapping
	public String Get(HttpServletRequest req, ModelMap model) throws UserNotFound {

		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req);

		// Check if logged in
		if (userNumber == null)
			return "redirect:/login";
		else {
			// Check if userNumber valid
			User user = new User();
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound

				// At this point, if no exception, userNumber is valid and user object is
				// retrieved
				// Check userType to decide which welcome page to redirect
				String userType = usrService.getUserType(userNumber); // throws UserNotFound
				switch (userType) {
				case "Member":
					return "redirect:/member";
				case "Admin":
					return "redirect:/admin";
				case "SuperAdmin":
					return "redirect:/superadmin";
				default:
					return "user/signup";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";

			}
		}
	}

	@GetMapping("/signup")
	public ModelAndView Signup(HttpServletRequest req, ModelMap model) {

		// Allowed for Guests (Non-user to sign up as member), Admin (to sign up for
		// member), SuperAdmin (to sign up for admin)
		// Hence, no need to retrieve and check userNumber from session
		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req); // to check if sign-up is done by Guest, Admin or SuperAdmin

		// Determining the accountType for the signup
		String acctType;
		try {
			String userType = usrService.getUserType(userNumber); // throws UserNotFound
			switch (userType) {
			case "Member":
				return new ModelAndView("redirect:/member"); // Member is not supposed to be able to sign up for other
																// accounts
			case "Admin":
				acctType = "Member"; // Admin is allowed to sign up for member, e.g. guest request counter staff to
										// sign up for them
			case "SuperAdmin":
				acctType = "Admin"; // SuperAdmin is allowed to sign up for admin
			default:
				acctType = "Member"; // Guests are allowed to sign up member accounts
			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus default
			acctType = "Member";
		}

		User user = new User();
		user.setPassword("Please enter a password");
		model.put("title", usrService.getTitleList()); // Retrieves title list to populate dropdownlist in signup form
		model.put("user", user); // Passes blank user object to fill up in signup form
		return new ModelAndView("user/signup");
	}

	@PostMapping("/signup")
	public String addNewUser(HttpServletRequest req, User user) {

		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req); // to check if sign-up is done by Guest, Admin or SuperAdmin

		// Determining the accountType for the signup
		String acctType;
		try {
			String userType = usrService.getUserType(userNumber); // throws UserNotFound
			switch (userType) {
			case "Member":
				return "redirect:home/member_home"; // Member is not supposed to be able to sign up for other accounts
			case "Admin":
				acctType = "Member"; // Admin is allowed to sign up for member, e.g. guest request counter staff to
										// sign up for them
			case "SuperAdmin":
				acctType = "Admin"; // SuperAdmin is allowed to sign up for admin
			default:
				acctType = "Member"; // Guests are allowed to sign up member accounts
			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus default
			acctType = "Member";
		}

		// Setting the appropriate userNumber for the user object based on acctType
		try {
			usrService.setNewUserNum(user, acctType); // throws UserNotFound
		} catch (UserNotFound e) {
			// This means that the "user" object passed into this method is null, thus
			// redirect back to signup page to restart
			return "user/signup";
		}

		// Perform validation Using UserValidator Class
		// Create UserValidator
		UserValidator uservalidator = new UserValidator();

		// Create DataBinder to Bind UserValidator
		DataBinder binder = new DataBinder(user);
		binder.setValidator(uservalidator);

		// Validate the Data
		binder.validate();

		// Check Results
		BindingResult results = binder.getBindingResult();
		System.out.println(user.toString());
		System.out.println(results.toString());
		if (results.hasErrors()) {
			// Provide feedback to user that an error has occurred and what are the actions
			// that can be taken

			// <!-- SAI PLEASE REPLACE THIS COMMENT WITH YOUR CODE FOR ERROR HANDLING. DO
			// NOT THROW EXCEPTION. YOU ARE THE HIGHEST LEVEL. NO ONE HIGHER TO CATCH -->
			return "user/signup"; // default redirection for guest/user to retry
		} else {
			// This means that there is no validation error
			try {
				usrService.addUser(user); // throws UserNotFound : this is to catch is the user object passed to the
											// addUser method is null
				System.out.println(user.toString());
				return "redirect:/login"; // This means that sign-up success
			} catch (UserNotFound e) {
				System.out.println(e.getMessage());
			}
		}
		return "user/signup"; // default redirection for guest/user to retry
	}

	@GetMapping("/profile")
	public String userProfile(HttpServletRequest req, ModelMap model) {

		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req); // to check user is login or not
		User user = new User();
		try {
			user = usrService.getUser(userNumber); // throws UserNotFound

			// At this point, if no exception, userNumber is valid and user object is
			// retrieved
			// Check userType to decide which welcome page to redirect
			if (user != null) {
				model.addAttribute("user", user);
				return "user/profile";
			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus return to login page
			return "redirect:/login";
		}
		return "redirect:/login";
	}

	@PostMapping("/profile")
	public String updateProfile(HttpServletRequest req, HttpServletResponse res, User user)
			throws ResourceDefinitionInvalid, UserNotFound {
		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req);// to check if sign-up is done by Guest, Admin or SuperAdmin

		User existinguser = new User();
		existinguser = usrService.getUser(userNumber);
		existinguser.setEmailAddress(user.getEmailAddress());
		existinguser.setPhoneNumber(user.getPhoneNumber());

		// Validation Using UserValidator Class
		System.out.println(existinguser.toString());

		UserValidator uservalidate = new UserValidator();
		DataBinder binder = new DataBinder(existinguser);
		binder.setValidator(uservalidate);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			return "user/profile";
		} else {

			usrService.updateUser(existinguser);
			return "redirect:/";
		}
	}

	@GetMapping("/changepassword")
	public String changePassword(HttpServletRequest req, ModelMap model) {
		String userNumber = this.getUserNumber(req);
		return "user/changepassword";
	}

	@PostMapping("/changepassword")
	public String updatePassword(HttpServletRequest req, ModelMap model) {
		
		String userNumber = this.getUserNumber(req);
		
		if (userNumber == null) {
			return "user/login";
		}
		else {
			// Check if userNumber valid
			User user = new User();
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound
				userNumber = user.getUserNumber();
				String currentpassword = req.getParameter("current_password");
				String newpassword = req.getParameter("password");
				String confirmpassword = req.getParameter("confirm_password");
				usrService.validatePasswordChange(userNumber, currentpassword, newpassword, confirmpassword);
					User existinguser = new User();
					existinguser = usrService.getUser(userNumber);

					System.out.println(existinguser.toString());
					
					existinguser = usrService.updatePassword(userNumber, newpassword);

					User updateuser = usrService.getUser(userNumber);
					System.out.println(updateuser.toString());

					return "user/profile";
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}			
		}
		
	}

	// get member list and bind data to manage member view
	// public String getMembers(HttpServletRequest req) {
	//
	// }
	//

	// Checking User Session and Get User Number
	public String getUserNumber(HttpServletRequest req) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Check if logged in
		if (userNumber == null)
			return "redirect:/login";
		else {
			// Check if userNumber valid
			User user = new User();
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound
				userNumber = user.getUserNumber();
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";

			}
		}

		return userNumber;

	}

	@ModelAttribute("user")
	public User user() {
		return new User();
	}

}
