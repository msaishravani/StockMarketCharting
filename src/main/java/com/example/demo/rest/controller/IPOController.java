package com.example.demo.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.IPO;
import com.example.demo.service.IPOService;

//@RestController
//@RequestMapping("/ipo")
public class IPOController {

	@Autowired
	private IPOService service;

	@RequestMapping("/")
	public ResponseEntity<List<IPO>> viewIPOs()
	{
		List<IPO> ipos=service.findAll();
		return ResponseEntity.ok(ipos);
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ResponseEntity<IPO> addIPO(@Valid @RequestBody IPO ipo)
	{
		service.save(ipo);
		
		return ResponseEntity.ok(ipo);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<IPO> getIPO(@PathVariable(name ="id") int id)
	{
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }
		
		return ResponseEntity.ok(service.findById(id));
	}

	@RequestMapping("/delete/{id}")
	public void deleteIPO(@PathVariable(name ="id") int id)
	{	
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }

		service.delete(id);

        return;
	}
}

