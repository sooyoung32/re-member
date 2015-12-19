package com.remember.server.service;

import com.google.common.collect.Lists;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.repository.IssueRepository;
import com.remember.server.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by NerdHerd on 2015. 12. 20..
 */

@Service
public class SubscriptionService {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

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

	public List<IssueEntity> getAllSubscribedIssues(UserEntity userEntity) {
		userEntity = userRepository.findOne(userEntity.getId());
		return userEntity.getSubscribedIssues();
	}

	public List<IssueEntity> getMainSubscribedIssues(UserEntity userEntity) {
		userEntity = userRepository.findOne(userEntity.getId());

		List<IssueEntity> result = userEntity.getSubscribedIssues().stream().limit(10).collect(Collectors.toList());

		if (result.size() < 10)
			result.addAll(getRandomIssues(result, 9));

		return result;
	}

	private List<IssueEntity> getRandomIssues(List<IssueEntity> issueEntities, int size) {

		List<IssueEntity> allIssueEntities = issueRepository.findAll();

		LinkedList<IssueEntity> result = new LinkedList<>();

		HashSet<ObjectId> dupSet = new HashSet<>();
		dupSet.addAll(issueEntities.stream().map(IssueEntity::getId).collect(Collectors.toList()));

		int count = 10;
		while (result.size() < size && count > 0) {
			IssueEntity newIssueEntity = getRandomIssue(allIssueEntities);

			if (dupSet.contains(newIssueEntity.getId())) {
				count--;
				continue;
			}

			dupSet.add(newIssueEntity.getId());
			result.addLast(newIssueEntity);

		}

		return result;
	}

	private IssueEntity getRandomIssue(List<IssueEntity> issueEntities) {

		Random randomizer = new Random();
		return issueEntities.get(randomizer.nextInt(issueEntities.size()));

	}

}
