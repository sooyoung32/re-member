package com.remember.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.remember.server.entity.IssueEntity;
import com.remember.server.model.DetailIssueModel;
import com.remember.server.model.NewIssueModel;
import com.remember.server.service.IssueService;

/**
 * Created by eunhwanpark on 15. 12. 19..
 */
@RestController
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/issue"
    )
    @ResponseBody
    public NewIssueModel uploadIssueArticle(
            @Valid @RequestBody NewIssueModel newIssueModel,
            @RequestHeader("AccessToken") String accessToken
    ) {
        IssueEntity issueEntity = issueService.createNewIssueArticle(newIssueModel);

        return modelMapper.map(
                issueEntity,
                NewIssueModel.class
        );
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/issue/{issueId}"
    )
    @ResponseBody
    public DetailIssueModel getIssueArticle(
            @RequestHeader("AccessToken") String accessToken,
            @PathVariable("issueId") String issueId
    ) {
        ObjectId issueOid = new ObjectId(issueId);

        IssueEntity issueEntity = issueService.getIssueArticle(issueOid);

        return modelMapper.map(
                issueEntity,
                DetailIssueModel.class
        );
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/recent/issues"
    )
    @ResponseBody
    public List<NewIssueModel> getRecentIssueArticles() {
    	List<IssueEntity> issueEntities = issueService.getRecentIssueArticles();
        return modelMapper.map(issueEntities, new TypeToken<List<NewIssueModel>>(){}.getType());
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/update/issues"
    )
    @ResponseBody
    public List<NewIssueModel> getUpdateIssueArticles() {
    	List<IssueEntity> issueEntities = issueService.getUpdateIssueArticles();
        return modelMapper.map(issueEntities, new TypeToken<List<NewIssueModel>>(){}.getType());
    }

}
