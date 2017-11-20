package com.yjs.blog.controller;

import com.yjs.blog.entity.User;
import com.yjs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yjs on 2017/11/20.
 */
public class BaseController {
    @Autowired
    UserService userService;

    public String getUid(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            String id = cookie.getValue();
            if (id != null) {
                User user = userService.findById(id);
                if (user != null) {
                    return id;

                }
            }
        }
        return null;
    }
}
