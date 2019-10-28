package com.infiniteloops.currencyconversionservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infiniteloops.currencyconversionservice.beans.CurrencyConversionBean;
import com.infiniteloops.currencyconversionservice.beans.CurrencyExchangeRatesBean;
import com.infiniteloops.currencyconversionservice.feign.services.FeignProxy;
import com.infiniteloops.currencyconversionservice.services.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

	@Autowired
	private FeignProxy feignProxy;
	
	@Autowired
	private CurrencyConversionService currencyConversionService;
	
	@RequestMapping("/convert/{from}/{to}/{amount}")
	public CurrencyConversionBean convert(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("amount") int amount)
	{
		final CurrencyExchangeRatesBean currencyExchangeRatesBean = feignProxy.getCurrencyExchangeRates(from, to);
		
		return currencyConversionService.calculate(currencyExchangeRatesBean, amount);
	}
}
