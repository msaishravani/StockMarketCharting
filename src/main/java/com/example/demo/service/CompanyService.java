package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repo;
	
	public void save(Company company)
	{
		repo.save(company);
	}
	
	public List<Company> findAll()
	{
		return repo.findAll();
	}
	
	public Company findById(int id)
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
