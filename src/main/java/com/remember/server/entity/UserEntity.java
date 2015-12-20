package com.remember.server.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode
@Document
public class UserEntity {

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;

	@Indexed(unique = true)
	private String email;

	private String name;

	private String encryptedPassword;
	
	private Date joinAt;

	@DBRef
	private List<IssueEntity> subscribedIssues;

	@Version
	private Long version;

}
