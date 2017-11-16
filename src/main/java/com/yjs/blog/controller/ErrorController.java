package com.yjs.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yjs on 2017/11/16.
 */
@Controller
public class ErrorController {

    @RequestMapping("/404")
    public String error(){
        return "404";
    }
}
