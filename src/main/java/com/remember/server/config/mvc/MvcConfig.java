package com.remember.server.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration

public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ApplicationContext context;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		argumentResolvers.add(context.getBean(IpAddressArgumentResolver.class));
		argumentResolvers.add(context.getBean(UserEntityArgumentResolver.class));
		argumentResolvers.add(context.getBean(SessionModelArgumentResolver.class));
	}


	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}