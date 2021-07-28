package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public String login(Model model)
	{
		return "login";
	}

	@RequestMapping("/loginaction")
	public String loginAction(@RequestParam String username,@RequestParam String password,Model model,HttpSession session)
	{
		User user=service.isValidUser(username, password);
		
		if(user!=null)
		{
			session.setAttribute("userid",user.getId());
			session.setAttribute("role",user.getRole());
			return "redirect:/viewcompanys";
		}
		else
		{
			model.addAttribute("message","Invalid Login Credentials");
			return "login";
		}
	}

	@RequestMapping(value = "/register")
	public String register(Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value = "/regaction",method = RequestMethod.POST)
	public String regAction(@ModelAttribute("user") User user,Model model)
	{
		if(!service.isExist(user.getUsername()))
		{
			user.setRole("user");
			service.save(user);
			model.addAttribute("message","Registred Successfully");
			return "login";
		}
		else
		{
			model.addAttribute("message","User Allready Exist");
			return "register";
		}
	}
	
	@RequestMapping("/viewprofile")
	public String viewprofile(HttpSession session,Model model)
	{
		model.addAttribute("user",service.findById((Integer)session.getAttribute("userid")));
		return "viewprofile";
	}
	
	@RequestMapping("/updateprofile")
	public String updateprofile(@ModelAttribute("user") User user,Model model)
	{
		service.save(user);
		model.addAttribute("user",user);
		return "viewprofile";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session,Model model)
	{
		System.out.println("in logout");
		session.invalidate();
		return "redirect:/";
	}
}

