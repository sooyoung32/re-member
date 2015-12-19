package com.remember.server.service;

import com.google.common.collect.Lists;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.repository.IssueRepository;
import com.remember.server.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NerdHerd on 2015. 12. 20..
 */

@Service
public class SubscriptionService {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	public void subscribe(ObjectId issueOid, UserEntity userEntity) {

		IssueEntity issueEntity = issueRepository.findOne(issueOid);

		userEntity = userRepository.findOne(userEntity.getId());

		List<IssueEntity> subscribedIssues = userEntity.getSubscribedIssues() == null ?
				Lists.newArrayList() :
				userEntity.getSubscribedIssues();

		if (subscribedIssues.stream().anyMatch(issue -> issue.getId().equals(issueEntity.getId())))
			return;

		subscribedIssues.add(subscribedIssues.size(), issueEntity);
		userEntity.setSubscribedIssues(subscribedIssues);

		userRepository.save(userEntity);
	}

	public void unsubscribe(ObjectId issueOid, UserEntity userEntity) {

		IssueEntity issueEntity = issueRepository.findOne(issueOid);

		userEntity = userRepository.findOne(userEntity.getId());
		List<IssueEntity> subscribedIssues = userEntity.getSubscribedIssues();
		List<IssueEntity> filteredIssues = subscribedIssues.stream().filter(issue -> !issue.getId().equals(issueEntity.getId())).collect(Collectors.toList());
		userEntity.setSubscribedIssues(filteredIssues);
		userRepository.save(userEntity);

	}

}
