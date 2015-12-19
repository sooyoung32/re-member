package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionModel {

	private String accessToken;
	private Date createdAt;
	private UserModel me;


}
