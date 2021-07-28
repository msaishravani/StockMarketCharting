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

import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyRestController {

	@Autowired
	private CompanyService service;

	@RequestMapping("/")
	public ResponseEntity<List<Company>> viewCompanys()
	{
		List<Company> companys=service.findAll();
		System.out.println("before return");
		return ResponseEntity.ok(companys);
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company)
	{
		service.save(company);
		
		return ResponseEntity.ok(company);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable(name ="id") int id)
	{
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }
		
		return ResponseEntity.ok(service.findById(id));
	}

	@RequestMapping("/delete/{id}")
	public void deleteCompany(@PathVariable(name ="id") int id)
	{	
		if(!service.isExist(id)) {
            ResponseEntity.badRequest().build();
        }

		service.delete(id);
		
		return;
	}
}

