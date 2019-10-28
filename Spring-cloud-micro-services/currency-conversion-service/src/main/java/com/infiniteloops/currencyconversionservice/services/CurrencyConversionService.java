package com.infiniteloops.currencyconversionservice.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.infiniteloops.currencyconversionservice.beans.CurrencyConversionBean;
import com.infiniteloops.currencyconversionservice.beans.CurrencyExchangeRatesBean;

@Component
public class CurrencyConversionService {

	public CurrencyConversionBean calculate(final CurrencyExchangeRatesBean currencyExchangeRatesBean, final int amount)
	{
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
		
		currencyConversionBean.setFrom(currencyExchangeRatesBean.getFrom());
		currencyConversionBean.setTo(currencyExchangeRatesBean.getTo());
		currencyConversionBean.setTotalCalculatedAmount(currencyExchangeRatesBean.getConversionMultiple().multiply(new BigDecimal(amount)));
		currencyConversionBean.setPort(currencyExchangeRatesBean.getPort());
		
		return currencyConversionBean;
	}
}
