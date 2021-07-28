package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange,Integer>{

}