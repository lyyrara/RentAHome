package com.example.rentahome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.rentahome.entity.User;
import com.example.rentahome.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
	UserService userService;

	@GetMapping("/")// "/" represents the very first page of your application 
	public String homePage() {
		
		System.out.println("inside homePage()");
		
		return "index";//This String "index" is supposed to be the name of the html/jsp name. Do not expect
						//"index" as a string to be returned form the server as a plain text...
	}

	@GetMapping("/signup_page")
	public String signupPage() {
		
		System.out.println("inside signupPage()");
		
		return "signup_page";
	}

	@PostMapping("/register")
	public ModelAndView registerUser(User user) {
		
		System.out.println("inside register()..."+user.getName()+", "+user.getPassword()+", "+user.getRole()+", "+user.getEmail());
		
		userService.addUser(user);
		
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("registerSuccess", "Registration Successfull, Now Please Login!!");
		
		return modelAndView;
	}

	@GetMapping("/login")
	public String loginPage() {
		
		System.out.println("inside signupPage()");
		
		return "login";
	}

    @PostMapping("/login")
	public ModelAndView login(String name, String password, HttpServletRequest request) {
		ModelAndView modelAndView;
		User user = userService.login(name, password);
		System.out.println(user);
		if(user != null){
			HttpSession session = request.getSession();
			modelAndView = new ModelAndView("index");
			session.setAttribute("loggedInUser", user);
		}
		else{
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("loginFailedStatus", "Login Failed, Please Try Again!!");
		}
		return modelAndView;
	}


		@GetMapping("/delete/{userId}")
		public ModelAndView deleteUser(@PathVariable int userId) {

			userService.deleteUser(userId);

			ModelAndView modelAndView = new ModelAndView("update_user");

			return modelAndView;
		}

		@GetMapping("/update_page/{userId}")
		public ModelAndView updateUserPage(@PathVariable int userId) {

			User user = userService.findByUserId(userId);

			//userService.updateUser(user);

			ModelAndView modelAndView = new ModelAndView("update_page");

			modelAndView.addObject("user", user);

			return modelAndView;
		}

		@PostMapping("/update")
		public ModelAndView updateUser(@RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam String role) throws InterruptedException {
			String updateStatus = userService.updateUser(name, password, email, role);
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("updateMsg", updateStatus);
			return modelAndView;
		}


		@GetMapping("/logout")
		public ModelAndView logout(HttpServletRequest request) {
			
			System.out.println("inside logout...");
			
			// Retrieve the current session, but don't create a new one if it doesn't exist
			HttpSession session = request.getSession(false);

			if (session != null) {
				// Retrieve the user from the session
				User loggedInUser = (User) session.getAttribute("loggedInUser");
		
				if (loggedInUser != null) {
					// Log the user's name if available
					System.out.println("User logging out: " + loggedInUser.getName());
				} else {
					// Handle the case where the user is not logged in
					System.out.println("No user logged in");
				}
		
				System.out.println("Session ID while logout is " + session.getId());
				
				// Invalidate the session
				session.invalidate();
			} else {
				System.out.println("No session found");
			}
			
			ModelAndView modelAndView = new ModelAndView("index");
			
			return modelAndView;
		}

		@GetMapping("/booking")// "/" represents the very first page of your application 
		public String bookingPage() {
			
			System.out.println("inside bookingPage()");
			
			return "booking";//This String "index" is supposed to be the name of the html/jsp name. Do not expect
							//"index" as a string to be returned form the server as a plain text...
		}
    
}
