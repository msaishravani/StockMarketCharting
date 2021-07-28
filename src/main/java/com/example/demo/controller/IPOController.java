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
import com.example.demo.model.Exchange;
import com.example.demo.model.IPO;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ExchangeService;
import com.example.demo.service.IPOService;

@Controller
public class IPOController {

	@Autowired
	private IPOService service;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	
	@RequestMapping("/displayipos")
	public String displayIpos(Model model,@RequestParam int companyId,@RequestParam int exchangeId)
	{
		List<IPO> ipos=service.findCompanyExchanges(companyId, exchangeId);
		model.addAttribute("ipos",ipos);
		
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "viewipos";
	}
	
	@RequestMapping("/viewipos")
	public String viewIPOs(Model model)
	{
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "viewipos";
	}
	
	@RequestMapping("/viewipo")
	public String viewIPO(@RequestParam int id,Model model)
	{
		model.addAttribute("ipo",service.findById(id));
		return "viewipo";
	}
	
	@RequestMapping("/addipo")
	public String addIPO(Model model)
	{
		model.addAttribute("ipo",new IPO());
		
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "addipo";
	}
	
	@RequestMapping(value = "/saveipo",method = RequestMethod.POST)
	public String saveIPO(HttpSession session, Model model,@ModelAttribute("ipo") IPO ipo)
	{
		service.save(ipo);
		model.addAttribute("message","IPO Saved Successfully");
		return "redirect:/viewipos";
	}
}

