package com.remember.server.model;

import com.remember.server.entity.ReferenceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Date;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordModel {

	private String id;

	private Date date;

	private String title;

	private String description;

	private List<ReferenceModel> references;

	private String imageUrl;

}
