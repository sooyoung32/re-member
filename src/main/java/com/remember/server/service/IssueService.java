package com.remember.server.service;

import com.remember.server.entity.IssueEntity;
import com.remember.server.model.NewIssueModel;
import com.remember.server.repository.IssueRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

    public IssueEntity createNewIssueArticle(NewIssueModel newIssueModel) {

        IssueEntity issueEntity = modelMapper.map(
                newIssueModel,
                IssueEntity.class
        );

        issueRepository.save(issueEntity);

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

}
