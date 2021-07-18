package com.example.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.pma.dao.UserAccountRepository;
import com.example.pma.entities.UserAccount;

@Controller
public class SecurityController {

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	UserAccountRepository userAccountRepo;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount=new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model,UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		userAccountRepo.save(user);
		return "redirect:/";
		 
	}
	
}
