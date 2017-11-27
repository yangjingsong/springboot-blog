package com.yjs.blog.controller;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.dao.CommentDao;
import com.yjs.blog.entity.Article;
import com.yjs.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by yjs on 2017/11/20.
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    CommentDao commentDao;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("articles", articleDao.findAll());
        return "front/index";

    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable(name = "id") String id) {
        Article article = articleDao.findById(id);
        Markdown markdown = new Markdown();

        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("article", article);
        model.addAttribute("articleId", id);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentDao.findByArticleId(id));
        return "front/detail";

    }

    @RequestMapping(value = "/writeComment/{articleId}", method = RequestMethod.POST)
    public String writeComment(HttpServletRequest request,
                               @PathVariable(name = "articleId") String articleId,
                               @ModelAttribute(value = "comment") Comment comment) {
        comment.setCommentId(getUid(request));
        comment.setArticleId(articleId);
        commentDao.save(comment);

        return "redirect:/blog/detail/" + articleId;
    }
}
