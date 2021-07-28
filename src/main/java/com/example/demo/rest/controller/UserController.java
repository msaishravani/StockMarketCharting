package com.example.demo.rest.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

//@RestController
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	public HashMap<String, Object> login(@RequestParam String username,@RequestParam String password,HttpSession session)
	{
		HashMap<String, Object> map = new HashMap<>();
		
		User user=service.isValidUser(username, password);
		
		if(user!=null)
		{
			session.setAttribute("userid",user.getId());
			session.setAttribute("role",user.getRole());
			
			map.put("status", "valid");
		}
		else
		{
			map.put("status", "valid");
		}
		
	    return map;
	}
	
	@RequestMapping("/")
	public ResponseEntity<List<User>> viewUsers()
	{
		List<User> users=service.findAll();
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public HashMap<String, Object> addUser(@Valid @RequestBody User user)
	{
		HashMap<String, Object> map = new HashMap<>();
		
		if(service.isExist(user.getUsername())) {
            map.put("status", "User Allready Exist");
        }
		else
		{
			service.save(user);
			map.put("status", "Registred Successfully");
		}
		
		return map;
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<User> getById(@PathVariable(name ="id") int id)
	{
		return ResponseEntity.ok(service.findById(id));
	}
	
	@RequestMapping("/get")
	public ResponseEntity<User> getUser(HttpSession session)
	{
		return ResponseEntity.ok(service.findById((Integer)session.getAttribute("userid")));
	}

	@RequestMapping("/delete/{id}")
	public void deleteUser(@PathVariable(name ="id") int id)
	{
		service.delete(id);
	}
}

