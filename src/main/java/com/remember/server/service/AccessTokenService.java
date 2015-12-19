package com.remember.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remember.server.entity.UserEntity;
import com.remember.server.exception.InvalidAccessTokenGenException;
import com.remember.server.model.SessionModel;
import com.remember.server.util.crypto.AES128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Service
public class AccessTokenService {

	private static String key = "fe8025947de7cd71";

	@Autowired
	private ObjectMapper objectMapper;

	private static class AccessTokenModel {
		private UserEntity userEntity;
		private SessionModel sessionModel;

		public AccessTokenModel() {
		}

		public AccessTokenModel(UserEntity userEntity, SessionModel sessionModel) {
			this.userEntity = userEntity;
			this.sessionModel = sessionModel;
		}

		public UserEntity getUserEntity() {
			return userEntity;
		}

		public SessionModel getSessionModel() {
			return sessionModel;
		}
	}

	public String createAccessToken(UserEntity userEntity, SessionModel sessionModel) throws InvalidAccessTokenGenException {

		try {
			AccessTokenModel accessTokenModel = new AccessTokenModel(userEntity, sessionModel);
			String jsonedValue = objectMapper.writeValueAsString(accessTokenModel);

			return AES128.encrypt(key, jsonedValue);
		} catch (Exception e) {
			throw new InvalidAccessTokenGenException(e);
		}

	}

	public void expire(String accessToken) {
	}

	public UserEntity getUserEntity(String accessToken) throws InvalidAccessTokenGenException {

		try {
			String jsonedValue = AES128.decrypt(key, accessToken);
			AccessTokenModel accessTokenModel = objectMapper.readValue(jsonedValue, AccessTokenModel.class);
			return accessTokenModel.getUserEntity();
		} catch (Exception e) {
			throw new InvalidAccessTokenGenException(e);
		}

	}

	public SessionModel getSessionModel(String accessToken) throws InvalidAccessTokenGenException {

		try {
			String jsonedValue = AES128.decrypt(key, accessToken);
			AccessTokenModel accessTokenModel = objectMapper.readValue(jsonedValue, AccessTokenModel.class);
			return accessTokenModel.getSessionModel();
		} catch (Exception e) {
			throw new InvalidAccessTokenGenException(e);
		}

	}

}
