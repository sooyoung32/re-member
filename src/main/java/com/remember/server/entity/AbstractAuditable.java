package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode
public abstract class AbstractAuditable {

	@DBRef
	@CreatedBy
	private UserEntity creator;

	@DBRef
	@LastModifiedBy
	private UserEntity modifier;

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date modifiedAt;

}
