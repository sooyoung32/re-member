package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewYoutubeRecordModel {

	@NotNull
	private Date date;

	@NotNull
	private String youtubeUrl;

	@NotNull
	private List<NewReferenceModel> references;

}