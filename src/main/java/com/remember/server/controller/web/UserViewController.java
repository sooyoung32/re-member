package com.remember.server.controller.web;

import com.remember.server.model.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by langerhans on 2015. 12. 20..
 */
@Controller
public class UserViewController {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/login"
    )
    public String login() {

        return "login";
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/signup"
    )
    public String signup() {

        return "signup";
    }
}
