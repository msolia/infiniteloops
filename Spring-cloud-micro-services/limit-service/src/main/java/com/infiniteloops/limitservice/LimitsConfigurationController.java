package com.infiniteloops.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infiniteloops.limitservice.bean.LimitConfigurationResponseDto;
import com.infiniteloops.limitservice.constants.ConfigurationPropertyConstants;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Environment environment;
	
	@RequestMapping("/limits")
	public LimitConfigurationResponseDto getLimitConfiguration()
	{
		LimitConfigurationResponseDto limitConfigurationResponseDto = 
				new LimitConfigurationResponseDto(
						environment.getProperty(ConfigurationPropertyConstants.MINIMUM, Integer.class),
						environment.getProperty(ConfigurationPropertyConstants.MAXIMUM, Integer.class));
		
		return limitConfigurationResponseDto;
		
	}
}
