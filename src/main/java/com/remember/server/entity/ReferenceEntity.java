package com.remember.server.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
public class ReferenceEntity {

	public ReferenceEntity() {
	}

	public ReferenceEntity(String url) {
		this.url = url;
	}

	@Id
	private ObjectId id;

	private String url;

	@Version
	private Long version;

}
