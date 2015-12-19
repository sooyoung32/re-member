package com.remember.server.service;

import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.model.NewIssueModel;
import com.remember.server.repository.IssueRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eunhwanpark on 15. 12. 19..
 */

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Autowired
	private RecordService recordService;

    public IssueEntity createNewIssueArticle(NewIssueModel newIssueModel, UserEntity userEntity) {


        IssueEntity issueEntity = modelMapper.map(
                newIssueModel,
                IssueEntity.class
        );
	    issueEntity.setRecordSize(1);
	    issueEntity.setCreator(userEntity);

        issueRepository.save(issueEntity);

	    recordService.createNewRecord(issueEntity.getId(), newIssueModel.getRecord(), userEntity);

        return issueEntity;
    }

    public IssueEntity getIssueArticle(ObjectId issueOid) {

        IssueEntity issueEntity = issueRepository.findOne(issueOid);
        return modelMapper.map(
                issueEntity,
                IssueEntity.class
        );
    }

    public List<IssueEntity> getRecentIssueArticles() {
        List<IssueEntity> issues = issueRepository.findTop3ByOrderByCreatedAtDesc();
        return issues;
    }
    
    public List<IssueEntity> getUpdateIssueArticles() {
        List<IssueEntity> issues = issueRepository.findTop3ByOrderByModifiedAtDesc();
        return issues;
    }

    public List<IssueEntity> getIssuePaginableArticles(int pageId) {
        List<IssueEntity> issues = issueRepository.findAll(new PageRequest(pageId, 10)).getContent();
        return issues;
    }

	public List<IssueEntity> getAllIssuesByUpdatedAt(int page) {
		List<IssueEntity> issues = issueRepository.findAll(new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "modifiedAt"))).getContent();
		return issues;
	}

	public List<IssueEntity> getAllIssuesBySubscribeCount(int page) {
		List<IssueEntity> issues = issueRepository.findAll(new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "subscribeCount"))).getContent();
		return issues;
	
	}

    public List<IssueEntity> searchIssues(int page, String title) {
        List<IssueEntity> issues = issueRepository.findByTitleContaining(title, new PageRequest(page, 10));
        return issues;
    }

	public List<IssueEntity> getAllIssuesByRecords(int pageId) {
		List<IssueEntity> issues = issueRepository.findAll(new PageRequest(pageId, 10, new Sort(Sort.Direction.ASC, "recordSize"))).getContent();
		return issues;
	}

}
