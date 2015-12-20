package com.remember.server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentModel {

	private String id;
	
	@NonNull
	private String content;

	private Date createdAt;
	
	@Version
	private Long version;
	
	
}
