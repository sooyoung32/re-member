package com.remember.server.controller;

import com.remember.server.entity.UserEntity;
import com.remember.server.model.CommentModel;
import com.remember.server.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by eunhwanpark on 15. 12. 20..
 */
@Slf4j
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/comment/{issueId}"
    )
    @ResponseBody
    public CommentModel addComment(
            @Valid @RequestBody CommentModel commentModel,
            @RequestHeader("AccessToken") String accessToken,
            @ApiIgnore UserEntity userEntity,
            @PathVariable("issueId") String issueId
    ) {
        return null;
    }
}
