package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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

	@NotNull
	private String title;

	@NotNull
	private String content;

	private List<TagEntity> tags;

	private long shareCount;

	@DBRef
	private List<RecordEntity> records;

	@DBRef
	private List<ActionEntity> actions;

	private String imageUrl;

	@Version
	private Long version;

}
