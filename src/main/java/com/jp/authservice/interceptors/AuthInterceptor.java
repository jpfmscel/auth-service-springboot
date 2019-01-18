package com.jp.authservice.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jp.authservice.services.AuthService;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {

	Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!shouldVerifyToken(request)) {
			return true;
		}

		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization) && !authorization.contains("Bearer ")) {
			response.setStatus(401);
			response.getOutputStream().print("Token not provided.");
			response.getOutputStream().flush();
			return false;
		}

		String token = authorization.split("Bearer ")[1];
		logger.info("Authorization header : " + authorization);
		logger.info("Token : " + token);
		try {
			authService.isTokenValid(token);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(401);
			response.getOutputStream().print("Token is invalid.");
			response.getOutputStream().flush();
		}

		return false;
	}

	private boolean shouldVerifyToken(HttpServletRequest request) {
		return !"/api/auth/login".equals(request.getServletPath());
	}

}
