package com.remember.server.controller;

import com.remember.server.entity.IssueEntity;
import com.remember.server.model.DetailIssueModel;
import com.remember.server.model.NewIssueModel;
import com.remember.server.service.IssueService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            value = "/v1/recent/issue"
    )
    @ResponseBody
    public NewIssueModel getRecentIssueArticles(
            @Valid @RequestBody NewIssueModel newIssueModel,
            @RequestHeader("AccessToken") String accessToken
    ) {
        return null;
    }


}
