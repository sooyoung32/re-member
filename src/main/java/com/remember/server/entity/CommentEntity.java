package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class CommentEntity extends AbstractAuditable {
	
	public CommentEntity() {
	}

	public CommentEntity(String content) {
		this.content = content;
	}

	@Id
	private ObjectId id;
	
	private String content;

	@Version
	private Long version;
	
	
}
