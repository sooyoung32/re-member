package com.remember.server.controller;

import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.RecordEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.exception.InvalidAccessTokenGenException;
import com.remember.server.model.*;
import com.remember.server.service.AccessTokenService;
import com.remember.server.service.RecordService;
import com.remember.server.util.crypto.SCRYPT;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

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
	public IssueModel postNewManualRecord(
			@Valid @RequestBody NewManualRecordModel newManualRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) {

		ObjectId issueOid = new ObjectId(issueId);

		IssueEntity issueEntity = recordService.createNewRecord(issueOid, newManualRecordModel);

		return modelMapper.map(
				issueEntity,
				IssueModel.class
		);


	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/issue/{issueId}/record/opengraph"
	)
	@ResponseBody
	public IssueModel postNewOpenGraphRecord(
			@Valid @RequestBody NewOpenGraphRecordModel newOpenGraphRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) throws IOException {

		ObjectId issueOid = new ObjectId(issueId);

		IssueEntity issueEntity = recordService.createNewRecord(issueOid, newOpenGraphRecordModel);

		return modelMapper.map(
				issueEntity,
				IssueModel.class
		);


	}

}
