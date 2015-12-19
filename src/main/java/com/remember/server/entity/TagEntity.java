package com.remember.server.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
public class TagEntity {

	@Id
	private ObjectId id;

	private String name;

}
