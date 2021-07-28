package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Exchange;
import com.example.demo.repository.ExchangeRepository;

@Service
public class ExchangeService {

	@Autowired
	private ExchangeRepository repo;
	
	public void save(Exchange exchange)
	{
		repo.save(exchange);
	}
	
	public List<Exchange> findAll()
	{
		return repo.findAll();
	}
	
	public Exchange findById(int id)
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
}
