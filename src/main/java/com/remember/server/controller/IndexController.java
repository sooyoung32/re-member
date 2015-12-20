package com.remember.server.controller;

import com.remember.server.entity.IssueEntity;
import com.remember.server.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by langerhans on 2015. 12. 20..
 */
@Controller
public class IndexController {

    @Autowired
    IssueService issueService;


    @RequestMapping("/")
    String index(Model model) {


        List<IssueEntity> recentIssueArticles = issueService.getRecentIssueArticles();
        List<IssueEntity> updateIssueArticles = issueService.getUpdateIssueArticles();

        model.addAttribute("recentIssueArticles", recentIssueArticles);
        model.addAttribute("updateIssueArticles", updateIssueArticles);

        return "index";
    }
}
