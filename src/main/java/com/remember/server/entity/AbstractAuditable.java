package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode
public abstract class AbstractAuditable {

	@CreatedBy
	private UserEntity creator;

	@LastModifiedBy
	private UserEntity modifier;

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date modifiedAt;

}
