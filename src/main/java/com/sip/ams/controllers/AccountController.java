package com.sip.ams.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams.entities.Role;
import com.sip.ams.entities.User;
import com.sip.ams.repositories.RoleRepository;
import com.sip.ams.repositories.UserRepository;
import java.util.*;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/accounts/")
public class AccountController {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	public AccountController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@GetMapping("list")
	public String listUsers(Model model) {
		List<User> users = (List<User>) userRepository.findAll();
		long nbr = userRepository.count();
		if (users.size() == 0)
			users = null;
		model.addAttribute("users", users);
		model.addAttribute("nbr", nbr);
		return "user/listUsers";
	}
	
	
	@PostMapping("updateRole")
	//@ResponseBody
	public String UpdateUserRole(@RequestParam ("id") int id,
	@RequestParam ("newrole")String newRole
	) {
	User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	Role userRole = roleRepository.findByRole(newRole);
	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	userRepository.save(user);
	return "redirect:list";
	}

}