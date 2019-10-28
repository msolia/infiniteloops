package com.infiniteloops.currencyexchangerateservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeRateJPA, Long>{
	
	ExchangeRateJPA findByFromAndTo(String from, String to);
	
}
