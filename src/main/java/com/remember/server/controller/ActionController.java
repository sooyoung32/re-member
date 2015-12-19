package com.remember.server.controller;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.remember.server.entity.ActionEntity;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.model.ActionModel;
import com.remember.server.model.NewManualRecordModel;
import com.remember.server.service.ActionService;

@Slf4j
@RestController
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
    @Autowired
    private ModelMapper modelMapper;
	

    @RequestMapping(
			method=RequestMethod.POST,
			value="/v1/issue/{issueId}/action"
			)
	@ResponseBody
	public void postNewAction(
			@Valid @RequestBody ActionModel actionModel,
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
			) {
		
		ObjectId issueOid = new ObjectId(issueId);
		actionService.createNewAction(issueOid, actionModel);
		
	}
    
}

