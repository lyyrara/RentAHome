package com.example.rentahome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.rentahome.entity.User;
import com.example.rentahome.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
	UserService userService;

    @PostMapping("/login")
	public ModelAndView login(String name, String password, HttpServletRequest request) {
		ModelAndView modelAndView;
		User user = userService.login(name, password);
		System.out.println(user);
		if(user != null){
			HttpSession session = request.getSession();
			modelAndView = new ModelAndView("welcome");
			session.setAttribute("loggedInUser", user);
		}
		else{
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("loginFailedStatus", "Login Failed, Please Try Again!!");
		}
		return modelAndView;
	}
    
}
