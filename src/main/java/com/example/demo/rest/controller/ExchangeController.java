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

import com.example.demo.model.Exchange;
import com.example.demo.service.ExchangeService;

//@RestController
//@RequestMapping("/exchange")
public class ExchangeController {

	@Autowired
	private ExchangeService service;

	@RequestMapping("/")
	public ResponseEntity<List<Exchange>> viewExchanges()
	{
		List<Exchange> exchanges=service.findAll();
		return ResponseEntity.ok(exchanges);
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ResponseEntity<Exchange> addExchange(@Valid @RequestBody Exchange exchange)
	{
		service.save(exchange);
		
		return ResponseEntity.ok(exchange);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<Exchange> getExchange(@PathVariable(name ="id") int id)
	{
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }
		
		return ResponseEntity.ok(service.findById(id));
	}

	@RequestMapping("/delete/{id}")
	public void deleteExchange(@PathVariable(name ="id") int id)
	{	
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }

		service.delete(id);

        return;
	}
}

