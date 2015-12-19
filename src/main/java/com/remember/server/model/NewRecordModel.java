package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRecordModel {

	private Date date;

	private String title;

	private String description;

	private List<ReferenceModel> references;

	private String imageUrl;

}