package com.remember.server.controller;

import com.remember.server.model.JoinModel;
import com.remember.server.model.LoginModel;
import com.remember.server.model.SessionModel;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@RestController
public class UserController {


	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/user"
	)
	@ResponseBody
	public SessionModel join(
			@Valid @RequestBody JoinModel joinModel,
			@RequestHeader("User-Agent") String userAgent,
			@ApiIgnore String ipAddress
	) {

		System.out.println("FUCK");
		return null;
	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/user/session"
	)
	@ResponseBody
	public SessionModel login(
			@Valid @RequestBody LoginModel loginModel,
			@RequestHeader("User-Agent") String userAgent,
			@ApiIgnore String ipAddress
	) {

		System.out.println("FUCK");
		return null;
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/v1/user/session"
	)
	@ResponseBody
	public void logout(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore HttpSession session
	) {

		System.out.println("FUCK");
	}

}
