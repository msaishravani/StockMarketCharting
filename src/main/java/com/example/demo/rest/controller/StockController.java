package com.example.demo.rest.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import com.example.demo.util.ExcelHelper;

//@RestController
//@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService service;

	@RequestMapping("/")
	public ResponseEntity<List<Stock>> viewStocks()
	{
		List<Stock> stocks=service.findAll();
		return ResponseEntity.ok(stocks);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ResponseEntity<Stock> addStock(@Valid @RequestBody Stock stock)
	{
		service.save(stock);

		return ResponseEntity.ok(stock);
	}

	@RequestMapping("/get/{id}")
	public ResponseEntity<Stock> getStock(@PathVariable(name ="id") int id)
	{
		if(!service.isExist(id)) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(service.findById(id));
	}

	@RequestMapping("/delete/{id}")
	public void deleteStock(@PathVariable(name ="id") int id)
	{	
		if(!service.isExist(id)) {
			ResponseEntity.badRequest().build();
		}

		service.delete(id);

		return;
	}

	@PostMapping("/upload")
	public HashMap<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {

		HashMap<String, Object> map = new HashMap<>();

		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				service.upload(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			}
		}
		else
		{
			message = "Please upload an excel file!";
		}

		map.put("status", message);

		return map;
	}
}

