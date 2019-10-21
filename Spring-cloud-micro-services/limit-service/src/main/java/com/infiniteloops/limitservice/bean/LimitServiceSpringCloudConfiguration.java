package com.infiniteloops.limitservice.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LimitServiceSpringCloudConfiguration {
	
	//If no property found for @Value, then it will check the property in application.properties.
	//if no property in application.properties found, it will return run time error. Like, ${minimum} not found.
	
    @Value("${minimum}")
	private int minimum;
	
    @Value("${maximum}")
	private int maximum;
    
    public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
}
