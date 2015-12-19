package com.remember.server.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class IpAddressArgumentResolver implements HandlerMethodArgumentResolver {

	public IpAddressArgumentResolver() {
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (!parameter.getParameterType().isAssignableFrom(String.class)) {
			return false;
		}
		return !(!parameter.getParameterName().toLowerCase().equals("ipaddress") &&
				!parameter.getParameterName().toLowerCase().equals("ipaddr"));
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory
	) throws Exception {
		String ipAddress;
		ipAddress = webRequest.getNativeRequest(HttpServletRequest.class).getRemoteAddr();
		if (!ipAddress.equals(""))
			return ipAddress;
		ipAddress = webRequest.getHeader("X-FORWARDED-FOR");
		return ipAddress;
	}

}