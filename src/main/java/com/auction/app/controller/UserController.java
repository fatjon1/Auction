package com.auction.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.auction.app.model.User;
import com.auction.app.repository.AuctionRepository;
import com.auction.app.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController {
	@NonNull UserRepository userRepository;
	@NonNull AuctionRepository auctionRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	@ExceptionHandler(NotFoundException.class)
	public String errorPage() {
		return "Index";
	}
	
	@RequestMapping("/secret/index")
	@ResponseBody
	public String textIndex(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@ModelAttribute("myUser") User user) {
		
//		String password=new BCryptPasswordEncoder().encode(user.getPassword());
//		user.setPassword(password);
		
//		if(userRepository.findByEmail(user.getEmail()) != null)
//			return "Email Already Registered";
		
		/*else if(userRepository.findByUsername(user.getUsername())!=null)
			return "Username Taken";
		
		else { 
			User userCreated=userRepository.save(user);
			return userCreated!=null ? "done" : "Something went wrong";
		}*/

		throw new RuntimeException("Not implemented");
		
	}
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(@ModelAttribute("myUser") User user,String ques, Model model, HttpServletRequest request, HttpServletResponse response) {
		/*User findUser=userRepository.findByEmail(user.getEmail());
		
		if(ques!=null) {
				if(user.getQuestion() == findUser.getQuestion() && findUser.getAnswer().equals(user.getAnswer())) {
					model.addAttribute("user", user);
					return "done";
				}
				
			return "Wrong Answer";
		}
		
		//Checking if email is valid  
		
		boolean isEmailValid=(findUser!=null);
		
		//Checking in try left for e-mail
		
		for(Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("emailTry") && !isEmailValid) {
				int count=Integer.parseInt(cookie.getValue());
				count=(isEmailValid ? count : count-1);
				cookie.setValue(count+"");
				response.addCookie(cookie);
				return count!=0 ? "Email Not Found You Have "+count+" Left !!!!" : "Banned";
			}
		}
		
		//If Cookie is not created then create a cookie
		if(!isEmailValid) {
			Cookie cookie=new Cookie("emailTry", "3");
			response.addCookie(cookie);
			return "Email Not Found You Have "+3+" Left !!!!";
		}
		@NonNull
		return ","+findUser.getEmail();*/
		throw new RuntimeException("Not implemented");
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(@ModelAttribute("myUser") User user) {
		/*User findUser=userRepository.findByEmail(user.getEmail());
		
		if(user.getPassword().length()<9)
			return "Password Length Must Be Greater Than 8";
		
		
		else if(user.getQuestion() == findUser.getQuestion() && findUser.getAnswer().equals(user.getAnswer())) {
			findUser.setPassword(user.getPassword());
			userRepository.save(findUser);
			return "";
		}*/
		
		return "Something went wrong <br> Try refreshing page";
	}
	
}
