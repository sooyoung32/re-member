package com.remember.server.controller;

import com.remember.server.entity.UserEntity;
import com.remember.server.model.NewYoutubeRecordModel;
import com.remember.server.model.SummarizedIssueModel;
import com.remember.server.service.SubscriptionService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 20..
 */

@RestController
public class SubscriptionController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/v1/issue/{issueId}/subscription"
	)
	public void subscribe(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) {
		ObjectId issueOid = new ObjectId(issueId);

		subscriptionService.subscribe(issueOid, userEntity);
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/v1/issue/{issueId}/subscription"
	)
	public void unsubscribe(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity,
			@PathVariable("issueId") String issueId
	) {
		ObjectId issueOid = new ObjectId(issueId);

		subscriptionService.unsubscribe(issueOid, userEntity);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/v1/me/subscribed"
	)
	public List<SummarizedIssueModel> getAllSubscribedIssues(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity
	) {

		return modelMapper.map(
				subscriptionService.getAllSubscribedIssues(userEntity),
				new TypeToken<List<SummarizedIssueModel>>(){}.getType()
		);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/v1/main/subscribed"
	)
	public List<SummarizedIssueModel> getMainSubscribedIssues(
			@RequestHeader("AccessToken") String accessToken,
			@ApiIgnore UserEntity userEntity
	) {

		return modelMapper.map(
				subscriptionService.getMainSubscribedIssues(userEntity),
				new TypeToken<List<SummarizedIssueModel>>(){}.getType()
		);
	}
}
