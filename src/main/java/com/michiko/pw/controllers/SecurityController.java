package com.michiko.pw.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.michiko.pw.dao.UserAccountRepostitory;
import com.michiko.pw.entities.UserAccount;

@Controller
public class SecurityController{
	
	@Autowired
	UserAccountRepostitory accountRep;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String register(Model model){
		
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, @Valid UserAccount user){				
				
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRep.save(user);
		
		return "redirect:/";
	}
}
