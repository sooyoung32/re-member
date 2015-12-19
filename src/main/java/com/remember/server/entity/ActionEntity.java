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
@EqualsAndHashCode(callSuper = true)
@Document
public class ActionEntity extends AbstractAuditable {

	public ActionEntity() {
	}

	public ActionEntity(String content, String url) {
		this.content = content;
		this.url = url;
	}

	@Id
	private ObjectId id;

	private String content;

	private String url;

	@Version
	private Long version;

}
