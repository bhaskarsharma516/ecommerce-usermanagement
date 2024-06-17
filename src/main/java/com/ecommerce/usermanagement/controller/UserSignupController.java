package com.ecommerce.usermanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.usermanagement.model.UserImage;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.service.UserDataService;
import com.ecommerce.usermanagement.service.UserRegService;
import com.ecommerce.usermanagement.utility.ImageUtils;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/user")
public class UserSignupController{	

	@Autowired
    private PasswordEncoder passwordEncoder ;
	

	@Autowired
    private UserRegService userRegService ;
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	

	@PostMapping("/registerprocess")
	public String userReg( UserProfile userProfile, @RequestParam("img") MultipartFile file,RedirectAttributes redirect)throws Exception{
		
		var imageToSave= UserImage.builder()
				.picName(file.getOriginalFilename())
				.picType(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes())).build();
		
		userProfile.setUserPassword(passwordEncoder.encode(userProfile.getUserPassword()));
		System.out.println(userProfile);
		Optional<UserProfile> userCreated = Optional.ofNullable(userRegService.usercreation(userProfile, imageToSave));
		if(userCreated.isEmpty()) 
			redirect.addFlashAttribute("error", "Phone number already exist");
		
		else
			redirect.addFlashAttribute("success","User created, procceed for login");
				
			return "redirect:/user/register";
			
		
//		
//		UserRegistrationResponse userResponse = UserRegistrationResponse.builder()
//				.name(userCreated.get().getName())
//				.userPhoneNumber(userCreated.get().getUserPhoneNumber())
//				.build();
//		return new ResponseEntity<UserRegistrationResponse>(userResponse, HttpStatus.CREATED);
		}
}

