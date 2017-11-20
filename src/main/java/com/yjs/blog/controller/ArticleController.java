package com.yjs.blog.controller;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.dao.CategoryDao;
import com.yjs.blog.entity.Article;
import com.yjs.blog.entity.Category;
import com.yjs.blog.entity.User;
import com.yjs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yjs on 2017/11/14.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @RequestMapping("/save")
    public String save(Article article, HttpServletRequest request) {

        String name = article.getCategory().getName();
        Category category = categoryDao.findByName(name);
        article.setCategory(category);

        if (article.getContent().length() > 40) {
            article.setSummary(article.getContent().substring(0, 40) + "...");
        } else {
            article.setSummary(article.getContent().substring(0, article.getContent().length()));
        }

        String uid = getUid(request);
        article.setDate(sdf.format(new Date()));
        article.setAuthorId(uid);

        articleDao.save(article);
        return "redirect:/admin/" + uid;

    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String articleId, Model model) {
        Article article = articleDao.findById(articleId);
        model.addAttribute("target", article);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("article", new Article());
        return "update";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpServletRequest request) {
        articleDao.delete(id);
        return "redirect:/admin/" + getUid(request);
    }


}
