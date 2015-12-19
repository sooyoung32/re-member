package com.remember.server.controller;

import java.util.List;

import javax.validation.Valid;

import com.remember.server.model.SummarizedIssueModel;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<SummarizedIssueModel> getRecentIssueArticles() {
    	List<IssueEntity> issueEntities = issueService.getRecentIssueArticles();
        return modelMapper.map(issueEntities, new TypeToken<List<SummarizedIssueModel>>(){}.getType());
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/update/issues"
    )
    @ResponseBody
    public List<SummarizedIssueModel> getUpdateIssueArticles() {
    	List<IssueEntity> issueEntities = issueService.getUpdateIssueArticles();
        return modelMapper.map(issueEntities, new TypeToken<List<SummarizedIssueModel>>(){}.getType());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/issues"
    )
    @ResponseBody
    public List<NewIssueModel> getIssue10Articles(
            @RequestHeader("AccessToken") String accessToken,
            @RequestParam(value = "pageId", required = false, defaultValue = "0") int pageId
    ) {
        List<IssueEntity> issueEntities = issueService.getIssuePaginableArticles(pageId);
        return modelMapper.map(issueEntities, new TypeToken<List<NewIssueModel>>(){}.getType());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/all/issues/update"
    )
    @ResponseBody
    public List<SummarizedIssueModel> getAllIssuesByUpdatedAt(
		    @RequestParam(value = "pageId", required = false, defaultValue = "0") int pageId
    ) {
        List<IssueEntity> issueEntities = issueService.getAllIssuesByUpdatedAt(pageId);
        return modelMapper.map(
		        issueEntities,
		        new TypeToken<List<SummarizedIssueModel>>(){}.getType()
        );
    }

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/v1/all/issues/subscription"
	)
	@ResponseBody
	public List<SummarizedIssueModel> getAllIssuesBySubscription(
			@RequestParam(value = "pageId", required = false, defaultValue = "0") int pageId
	) {
		List<IssueEntity> issueEntities = issueService.getAllIssuesBySubscribeCount(pageId);
		return modelMapper.map(
				issueEntities,
				new TypeToken<List<SummarizedIssueModel>>(){}.getType()
		);
	}

}
