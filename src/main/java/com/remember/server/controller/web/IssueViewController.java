package com.remember.server.controller.web;

import com.remember.server.entity.IssueEntity;
import com.remember.server.model.DetailIssueModel;
import com.remember.server.model.NewIssueModel;
import com.remember.server.model.SummarizedIssueModel;
import com.remember.server.service.IssueService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by langerhans on 2015. 12. 20..
 */
@Controller
public class IssueViewController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IssueService issueService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/issues"
    )
    public String getIssue10Articles(
            @RequestParam(value = "pageId", required = false, defaultValue = "0") int pageId,
            Model model

    ) {
        List<IssueEntity> issueEntities = issueService.getIssuePaginableArticles(pageId);

        model.addAttribute("issues", modelMapper.map(issueEntities, new TypeToken<List<SummarizedIssueModel>>(){}.getType()));

        return "issues";
    }



    @RequestMapping(
            method = RequestMethod.GET,
            value = "/issue/{issueId}"
    )
    public String getIssueArticle(
            @PathVariable("issueId") String issueId,
            Model model
    ) {
        ObjectId issueOid = new ObjectId(issueId);

        IssueEntity issueEntity = issueService.getIssueArticle(issueOid);

        model.addAttribute("issue", modelMapper.map(
                issueEntity,
                DetailIssueModel.class
        ));

        return "issue";

    }
}
