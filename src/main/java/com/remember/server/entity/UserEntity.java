package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode
@Document
public class UserEntity {

	@Id
	private ObjectId id;

	private String email;

	private String name;

	private String encryptedPassword;

	@Version
	private Long version;

}
