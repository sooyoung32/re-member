package com.remember.server.config.mvc;

import com.remember.server.service.AccessTokenService;
import com.remember.server.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class UserEntityArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private AccessTokenService accessTokenService;

	public UserEntityArgumentResolver() {
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		if (!parameter.getParameterType().isAssignableFrom(UserEntity.class)) {
			return false;
		}
		return !(!parameter.getParameterName().toLowerCase().equals("user") &&
				!parameter.getParameterName().toLowerCase().equals("userentity"));
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory
	) throws Exception {
		String accessToken = webRequest.getHeader("AccessToken");
		return accessTokenService.getUserEntity(accessToken);
	}

}