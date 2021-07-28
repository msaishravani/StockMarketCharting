package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Company;
import com.example.demo.model.Exchange;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ExchangeService;
import com.example.demo.service.StockService;
import com.example.demo.util.ExcelHelper;

@Controller
public class StockController {

	@Autowired
	private StockService service;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@RequestMapping("/displaystocks")
	public String displayStocks(@RequestParam int companyId,@RequestParam int exchangeId,Model model,HttpSession session)
	{
		List<Stock> stocks=service.findCompanyStock(companyId, exchangeId);
		session.setAttribute("stocks",stocks);
		
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "viewstocks";
	}
	
	@RequestMapping("/viewstocks")
	public String viewStocks(Model model)
	{
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "viewstocks";
	}
	
	@RequestMapping("/addstock")
	public String addStock(Model model)
	{
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "addstock";
	}
	
	@RequestMapping(value = "/uploadstock",method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file,Model model,@RequestParam int companyId,@RequestParam int exchangeId) {
		
		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				service.upload(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				e.printStackTrace();
			}
		}
		else
		{
			message = "Please upload an excel file!";
		}

		List<Stock> stocks=service.findCompanyStock(companyId, exchangeId);
		model.addAttribute("stocks",stocks);
		model.addAttribute("message",message);
		
		return "viewstocks";
	}
	
	@RequestMapping("/comparestock")
	public String compareStock(Model model,HttpSession session)
	{
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		return "comparestock";
	}
	
	@RequestMapping("/displaycomparestock")
	public String displayCompareStock(@RequestParam int company1,@RequestParam int exchange1,@RequestParam int company2,@RequestParam int exchange2,Model model,HttpSession session)
	{
		List<Company> companys=companyService.findAll();
		model.addAttribute("companys",companys);
		
		List<Exchange> exchanges=exchangeService.findAll();
		model.addAttribute("exchanges",exchanges);
		
		List<Stock> stocks1=service.findCompanyStock(company1, exchange1);
		List<Stock> stocks2=service.findCompanyStock(company2, exchange2);
		
		session.setAttribute("stocks1",stocks1);
		session.setAttribute("stocks2",stocks2);
		
		return "comparestock";
	}
}

