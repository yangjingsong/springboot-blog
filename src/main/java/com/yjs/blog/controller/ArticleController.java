package com.yjs.blog.controller;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.dao.CategoryDao;
import com.yjs.blog.entity.Article;
import com.yjs.blog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yjs on 2017/11/14.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @RequestMapping("/save")
    public String save(Article article) {

        String name = article.getCategory().getName();
        Category category = categoryDao.findByName(name);
        article.setCategory(category);

        if (article.getContent().length() > 40) {
            article.setSummary(article.getContent().substring(0, 40)+"...");
        } else {
            article.setSummary(article.getContent().substring(0, article.getContent().length()));
        }

        article.setDate(sdf.format(new Date()));

        articleDao.save(article);
        return "redirect:/admin";

    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String articleId, Model model) {
        Article article= articleDao.findById(articleId);
        model.addAttribute("target",article);
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("article",new Article());
        return "update";
    }
}
