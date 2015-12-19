package com.remember.server.controller;

import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.model.*;
import com.remember.server.service.RecordService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@RestController
public class RecordController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RecordService recordService;

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/issue/{issueId}/record/manual"
	)
	@ResponseBody
	public NewIssueModel postNewManualRecord(
			@Valid @RequestBody NewManualRecordModel newManualRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) {

		ObjectId issueOid = new ObjectId(issueId);

		IssueEntity issueEntity = recordService.createNewRecord(issueOid, newManualRecordModel, userEntity);

		return modelMapper.map(
				issueEntity,
				NewIssueModel.class
		);

	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/issue/{issueId}/record/opengraph"
	)
	@ResponseBody
	public NewIssueModel postNewOpenGraphRecord(
			@Valid @RequestBody NewOpenGraphRecordModel newOpenGraphRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) throws IOException {

		ObjectId issueOid = new ObjectId(issueId);

		IssueEntity issueEntity = recordService.createNewRecord(issueOid, newOpenGraphRecordModel, userEntity);

		return modelMapper.map(
				issueEntity,
				NewIssueModel.class
		);

	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/issue/{issueId}/record/youtube"
	)
	@ResponseBody
	public NewIssueModel postNewYoutubeRecord(
			@Valid @RequestBody NewYoutubeRecordModel newYoutubeRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) throws IOException {

		ObjectId issueOid = new ObjectId(issueId);

		IssueEntity issueEntity = recordService.createNewRecord(issueOid, newYoutubeRecordModel, userEntity);

		return modelMapper.map(
				issueEntity,
				NewIssueModel.class
		);

	}

}
