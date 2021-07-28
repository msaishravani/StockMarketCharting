package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import com.example.demo.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@RequestMapping("/viewcompanys")
	public String viewCompanys(Model model)
	{
		List<Company> companys=service.findAll();
		model.addAttribute("companys",companys);
		return "viewcompanys";
	}
	
	@RequestMapping("/viewcompany")
	public String viewCompany(@RequestParam int id,Model model)
	{
		model.addAttribute("company",service.findById(id));
		return "viewcompany";
	}
	
	@RequestMapping("/addcompany")
	public String addCompany(Model model)
	{
		model.addAttribute("company",new Company());
		return "addcompany";
	}
	
	@RequestMapping(value = "/savecompany",method = RequestMethod.POST)
	public String saveCompany(HttpSession session, Model model,@ModelAttribute("company") Company company)
	{
		service.save(company);
		model.addAttribute("message","Company Saved Successfully");
		return "redirect:/viewcompanys";
	}
	
	@RequestMapping("/activatecompany")
	public String activateCompany(@RequestParam Integer companyid,@RequestParam String status,Model model)
	{
		Company company=service.findById(companyid);
		company.setStatus(status);
		
		service.save(company);
		
		List<Company> companys=service.findAll();
		model.addAttribute("companys",companys);
		return "viewcompanys";
	}
}

