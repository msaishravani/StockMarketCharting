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

import com.example.demo.model.Exchange;
import com.example.demo.service.ExchangeService;

@Controller
public class ExchangeController {

	@Autowired
	private ExchangeService service;
	
	@RequestMapping("/viewexchanges")
	public String viewExchanges(Model model)
	{
		List<Exchange> exchanges=service.findAll();
		
		model.addAttribute("exchanges",exchanges);
		
		return "viewexchanges";
	}
	
	@RequestMapping("/viewexchange")
	public String viewExchange(@RequestParam int id,Model model)
	{
		model.addAttribute("exchange",service.findById(id));
		return "viewexchange";
	}
	
	@RequestMapping("/addexchange")
	public String addExchange(Model model)
	{
		model.addAttribute("exchange",new Exchange());
		return "addexchange";
	}
	
	@RequestMapping(value = "/saveexchange",method = RequestMethod.POST)
	public String saveExchange(HttpSession session, Model model,@ModelAttribute("exchange") Exchange exchange)
	{
		service.save(exchange);
		model.addAttribute("message","Exchange Saved Successfully");
		return "redirect:/viewexchanges";
	}
}

