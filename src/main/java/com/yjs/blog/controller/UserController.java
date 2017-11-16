package com.yjs.blog.controller;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.dao.CategoryDao;
import com.yjs.blog.entity.Article;
import com.yjs.blog.entity.User;
import com.yjs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yjs on 2017/11/14.
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ArticleDao articleDao;

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model, HttpServletResponse response) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return "login";
        }
        for (Cookie c : cookies) {
            String username = c.getValue();
            if (username != null) {
                User user = userService.findByName(username);
                if (user != null) {
                    return "redirect:/admin";
                }
            }

        }
        return "login";
    }

    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request,HttpServletResponse response, User user, Model model) {

        if (userService.login(user.getUsername(), user.getPassword())) {
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");//整个应用路径都可使用该cookie
            //cookie.setDomain("http://localhost:8081/");
            model.addAttribute("user", user);
            response.addCookie(cookie);
            request.getSession().setAttribute("user",user );
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "用户名密码错误");
            return "login";
        }
    }

    @RequestMapping("")
    public String articleIndex(Model model) {
        model.addAttribute("articles", articleDao.findAll());
        return "index";
    }

    @RequestMapping("/write")
    public String write(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryDao.findAll());
        return "write";
    }



}
