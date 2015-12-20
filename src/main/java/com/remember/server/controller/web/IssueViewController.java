package com.remember.server.controller.web;

import com.remember.server.entity.IssueEntity;
import com.remember.server.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by langerhans on 2015. 12. 20..
 */
@Controller
public class IssueViewController {

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

        model.addAttribute("issues", issueEntities);

        return "issues";
    }
}
