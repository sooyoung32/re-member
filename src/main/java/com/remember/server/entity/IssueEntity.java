package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class IssueEntity extends AbstractAuditable {

	@Id
	private ObjectId id;

	private String title;

	private List<TagEntity> tags;

	private long shareCount;

	@DBRef
	private List<RecordEntity> records;

	@DBRef
	private List<ActionEntity> actions;

	@Version
	private Long version;

}
