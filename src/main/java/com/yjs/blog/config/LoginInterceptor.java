package com.yjs.blog.config;

import com.yjs.blog.entity.User;
import com.yjs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yjs on 2017/11/15.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || userService.findByName(user.getUsername()) == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return true;
        }

        return super.preHandle(request, response, handler);
    }
}
