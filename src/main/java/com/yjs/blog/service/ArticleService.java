package com.yjs.blog.service;

import com.yjs.blog.dao.ArticleDao;
import com.yjs.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yjs on 2017/11/14.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public void save(Article article){
        articleDao.save(article);
    }
}
