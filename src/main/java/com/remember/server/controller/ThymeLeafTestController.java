package com.remember.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class ThymeLeafTestController {

    @RequestMapping("/greeting")
    public String greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "greeting";
    }

	@RequestMapping("properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}

}