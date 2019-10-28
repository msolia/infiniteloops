package com.infiniteloops.currencyconversionservice.feign.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infiniteloops.currencyconversionservice.beans.CurrencyExchangeRatesBean;

//Below can be use if NO ribbon.listOfServers is defined in application.properties
//@FeignClient(name="currency-exchange-service", url="localhost:8881")

//Below can be use if ribbon.listOfServers is defined in application.properties
@FeignClient(name="currency-exchange-service")
public interface  FeignProxy {
	
	@RequestMapping("/exchange-rate/{from}/{to}")
	public CurrencyExchangeRatesBean getCurrencyExchangeRates(@PathVariable("from") String from, @PathVariable("to") String to);

}
	