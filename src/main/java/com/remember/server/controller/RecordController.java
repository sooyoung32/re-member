package com.remember.server.controller;

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
			value = "/v1/issue/{issueId}/record"
	)
	@ResponseBody
	public RecordModel postNewRecord(
			@Valid @RequestBody NewRecordModel newRecordModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) {

		ObjectId issueOid = new ObjectId(issueId);

		RecordEntity recordEntity = recordService.createNewRecord(issueOid, newRecordModel);

		return modelMapper.map(
				recordEntity,
				RecordModel.class
		);


	}

}
