package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.util.ExcelHelper;

@Service
public class StockService {

	@Autowired
	private StockRepository repo;

	public void save(Stock stock)
	{
		repo.save(stock);
	}

	public List<Stock> findAll()
	{
		return repo.findAll();
	}

	public Stock findById(int id)
	{
		return repo.findById(id).get();
	}

	public boolean isExist(int id)
	{
		return repo.existsById(id);
	}

	public void delete(int id)
	{
		repo.deleteById(id);
	}
	
	public List<Stock> findCompanyStock(int companyId,int exchangeId)
	{
		List<Stock> stocks=new ArrayList<Stock>();
		
		for (Stock stock: repo.findAll())
		{
			if(stock.getCompanyId()==companyId && stock.getExchangeId()==exchangeId)
			{
				stocks.add(stock);
			}
		}
		return stocks;
	}

	public void upload(MultipartFile file) {
		try {
			List<Stock> stocks = ExcelHelper.excelToStocks(file.getInputStream());
			repo.saveAll(stocks);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}
}
