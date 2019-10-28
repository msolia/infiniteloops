package com.infiniteloops.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infiniteloops.limitservice.bean.CmsSiteServiceSpringCloudConfiguration;
import com.infiniteloops.limitservice.bean.LimitConfigurationResponseDto;
import com.infiniteloops.limitservice.bean.LimitServiceSpringCloudConfiguration;
import com.infiniteloops.limitservice.constants.ConfigurationPropertyConstants;

@RestController
@RefreshScope
public class LimitsConfigurationController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private LimitServiceSpringCloudConfiguration limitServiceConfig;
	
	@Autowired
	private CmsSiteServiceSpringCloudConfiguration cmsSiteServiceConfig;
	
	/**
	 * Return Limit configuration json values from limit-service application.properties
	 * @return
	 */
	@RequestMapping("/limits/not/spring-cloud-config")
	public LimitConfigurationResponseDto getLimitConfiguration()
	{
		LimitConfigurationResponseDto limitConfigurationResponseDto = 
				new LimitConfigurationResponseDto(
						environment.getProperty(ConfigurationPropertyConstants.MINIMUM, Integer.class),
						environment.getProperty(ConfigurationPropertyConstants.MAXIMUM, Integer.class));
		
		return limitConfigurationResponseDto;
		
	}
	
	
	/**
	 * Return Limit configuration json values from spring cloud config server running at localhost:8888
	 * @return
	 */
	@RequestMapping("/limits/spring-cloud-config")
	public LimitConfigurationResponseDto getLimitConfigurationFromSpringCloud()
	{
		LimitConfigurationResponseDto limitConfigurationResponseDto = 
				new LimitConfigurationResponseDto(
						limitServiceConfig.getMinimum(), 
						limitServiceConfig.getMaximum(),
						cmsSiteServiceConfig.getName());
		
		return limitConfigurationResponseDto;
		
	}
	
}
