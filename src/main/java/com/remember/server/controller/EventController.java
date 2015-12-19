package com.remember.server.controller;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * Created by eunhwanpark on 15. 12. 19..
 */
@RestController
public class EventController {

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/event"
    )
    @ResponseBody
    public void uploadEvent(
            @RequestHeader("AccessToken") String accessToken
    ) {

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/event"
    )
    @ResponseBody
    public void getEvent(
            @RequestHeader("AccessToken") String accessToken
    ) {

    }
}
