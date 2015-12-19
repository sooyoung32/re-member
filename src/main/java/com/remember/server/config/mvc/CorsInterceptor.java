package com.remember.server.config.mvc;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsInterceptor implements HandlerInterceptor
{
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS     = "Access-Control-Allow-Headers";
    private static final String REQUEST_HEADER_ORIGIN            = "Origin";
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String origin = request.getHeader(REQUEST_HEADER_ORIGIN);
 
        // CORS 가능하도록 응답 헤더 추가
        if (StringUtils.hasLength(origin))
        {
            // 요청한 도메인에 대해 CORS 를 허용한다. 제한이 필요하다면 필요한 값으로 설정한다.
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);

            // credentials 허용
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        } else {
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");

            // credentials 허용
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }

	    response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, "AccessToken");

//	    response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");

	    // credentials 허용
//	    response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
         
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        // do nothing
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // do nothing
    }
}