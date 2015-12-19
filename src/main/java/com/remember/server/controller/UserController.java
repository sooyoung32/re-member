package com.remember.server.controller;

import com.remember.server.AccessTokenService;
import com.remember.server.entity.UserEntity;
import com.remember.server.exception.InvalidAccessTokenGenException;
import com.remember.server.exception.InvalidCredentialException;
import com.remember.server.model.JoinModel;
import com.remember.server.model.LoginModel;
import com.remember.server.model.SessionModel;
import com.remember.server.model.UserModel;
import com.remember.server.repository.UserRepository;
import com.remember.server.util.crypto.SCRYPT;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@RestController
public class UserController {

	@Autowired
	private AccessTokenService accessTokenService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/user"
	)
	@ResponseBody
	public SessionModel join(
			@Valid @RequestBody JoinModel joinModel,
			@RequestHeader("User-Agent") String userAgent,
			@ApiIgnore String ipAddress
	) throws InvalidAccessTokenGenException {

		UserEntity newUserEntity = new UserEntity();
		newUserEntity.setEmail(joinModel.getEmail());
		newUserEntity.setEncryptedPassword(SCRYPT.encrypt(joinModel.getPassword()));
		userRepository.save(newUserEntity);

		SessionModel sessionModel = new SessionModel(
				null,
				new Date(),
				modelMapper.map(newUserEntity, UserModel.class)
		);

		String accessToken = accessTokenService.createAccessToken(newUserEntity, sessionModel);
		sessionModel.setAccessToken(accessToken);
		return sessionModel;

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
	) throws InvalidCredentialException, InvalidAccessTokenGenException {

		UserEntity userEntity = userRepository.findOneByEmail(loginModel.getEmail());

		if (userEntity == null)
			throw new InvalidCredentialException();

		boolean passwordValid = SCRYPT.check(
				loginModel.getPassword(),
				userEntity.getEncryptedPassword()
		);

		if (!passwordValid)
			throw new InvalidCredentialException();

		SessionModel sessionModel = new SessionModel(
				null,
				new Date(),
				modelMapper.map(userEntity, UserModel.class)
		);

		String accessToken = accessTokenService.createAccessToken(userEntity, sessionModel);
		sessionModel.setAccessToken(accessToken);
		return sessionModel;

	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/v1/user/session"
	)
	@ResponseBody
	public void logout(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore SessionModel sessionModel
	) {

		accessTokenService.expire(accessToken);

	}

}
