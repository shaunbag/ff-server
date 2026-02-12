package com.shaunbag.ff_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping(value = { "/app" })
    public String forward() {
        return "forward:/index.html";
    }
}
