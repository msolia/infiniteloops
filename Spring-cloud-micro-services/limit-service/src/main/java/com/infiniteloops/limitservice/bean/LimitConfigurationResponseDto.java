package com.infiniteloops.limitservice.bean;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LimitConfigurationResponseDto {

	@NotNull
	private int minimum;
	
	@NotNull
	private int maximum;
	
	@NotNull
	private String name;
	
	public LimitConfigurationResponseDto(final int minimum, final int maximum)
	{
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	public LimitConfigurationResponseDto(final int minimum, final int maximum, final String name)
	{
		this.minimum = minimum;
		this.maximum = maximum;
		this.name = name;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	@JsonProperty
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
