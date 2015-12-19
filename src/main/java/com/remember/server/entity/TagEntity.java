package com.remember.server.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
public class TagEntity {

	public TagEntity() {
	}

	public TagEntity(String name) {
		this.name = name;
	}

	@Id
	private ObjectId id;

	private String name;

}
