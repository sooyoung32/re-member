package com.remember.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class RecordEntity extends AbstractAuditable {

	@Id
	private ObjectId id;

	private Date date;

	private String title;

	private String description;

	private List<ReferenceEntity> references;

	private String imageUrl;

	private String openGraphUrl;

	private String youtubeUrl;

	@Version
	private Long version;

}
