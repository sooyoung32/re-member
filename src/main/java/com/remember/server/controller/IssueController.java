package com.remember.server.controller;

import com.remember.server.model.IssueModel;
import com.remember.server.repository.IssueRepository;
import com.remember.server.service.IssueService;
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
    private IssueRepository issueRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/issue"
    )
    @ResponseBody
    public void uploadIssueArticle(
            @Valid @RequestBody IssueModel issueModel,
            @RequestHeader("AccessToken") String accessToken
    ) {

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/issue"
    )
    @ResponseBody
    public void getIssueArticle(
            @Valid @RequestBody IssueModel issueModel,
            @RequestHeader("AccessToken") String accessToken
    ) {

    }




}
