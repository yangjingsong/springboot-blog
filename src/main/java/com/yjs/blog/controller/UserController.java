package com.yjs.blog.controller;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.dao.CategoryDao;
import com.yjs.blog.entity.Article;
import com.yjs.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yjs on 2017/11/14.
 */
@Controller
@RequestMapping("/admin")
public class UserController extends BaseController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ArticleDao articleDao;

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model, HttpServletResponse response) {
        String uid = getUid(request);
        if (uid != null) {
            return "redirect:/admin/" + uid;
        }
        return "login";
    }

    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response, User user, Model model) {

        User u = userService.login(user.getUsername(), user.getPassword());
        if (u != null) {
            Cookie cookie = new Cookie("id", u.getId());
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");//整个应用路径都可使用该cookie
            model.addAttribute("user", user);
            response.addCookie(cookie);
            request.getSession().setAttribute("user", user);
            return "redirect:/admin/" + u.getId();
        } else {
            model.addAttribute("error", "用户名密码错误");
            return "login";
        }
    }

    @RequestMapping("/{uId}")
    public String articleIndex(Model model, @PathVariable("uId") String uId) {
        model.addAttribute("articles", articleDao.findByAuthorId(uId));
        return "index";
    }

    @RequestMapping("/write")
    public String write(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryDao.findAll());
        return "write";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookies[] = request.getCookies();
        for (Cookie c : cookies) {
            String id = c.getValue();
            if (id != null) {
                User user = userService.findById(id);
                if (user != null) {
                    Cookie cookie = new Cookie("id", user.getId());
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public String doRegister(HttpServletResponse response, HttpServletRequest request, Model model, User user) {
        if (userService.findByName(user.getUsername()) != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        } else {
            User u = userService.insert(user);
            Cookie cookie = new Cookie("id", u.getId());
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");//整个应用路径都可使用该cookie
            model.addAttribute("user", user);
            response.addCookie(cookie);
            request.getSession().setAttribute("user", u);
            return "redirect:/admin/" + u.getId();
        }

    }

}
