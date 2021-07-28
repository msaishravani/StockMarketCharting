package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.IPO;
import com.example.demo.repository.IPORepository;

@Service
public class IPOService {

	@Autowired
	private IPORepository repo;
	
	public void save(IPO iPO)
	{
		repo.save(iPO);
	}
	
	public List<IPO> findAll()
	{
		return repo.findAll();
	}
	public IPO findById(int id)
	{
		return repo.findById(id).get();
	}
	
	public List<IPO> findCompanyExchanges(int companyId,int exchangeId)
	{
		List<IPO> ipos=new ArrayList<IPO>();
		
		for (IPO ipo: repo.findAll())
		{
			if(ipo.getCompanyId()==companyId && ipo.getExchangeId()==exchangeId)
			{
				ipos.add(ipo);
			}
		}
		return ipos;
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
