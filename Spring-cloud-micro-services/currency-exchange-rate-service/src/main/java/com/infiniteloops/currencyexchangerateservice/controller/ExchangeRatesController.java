package com.infiniteloops.currencyexchangerateservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infiniteloops.currencyexchangerateservice.jpa.ExchangeRateJPA;
import com.infiniteloops.currencyexchangerateservice.jpa.ExchangeValueRepository;

@RestController
public class ExchangeRatesController {

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@Autowired
	private Environment environment;
	
	/**
	 * Exchange rate request mapping
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping("/exchange-rate/{from}/{to}")
	public ExchangeRateJPA getExchangeRateValue(@PathVariable ("from") String from, @PathVariable("to") String to)
	{
		ExchangeRateJPA exchangeRateJPA = exchangeValueRepository.findByFromAndTo(from, to);
		
		exchangeRateJPA.setPort(
				Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeRateJPA;
	}
}
