package com.yjs.blog.dao;

import com.yjs.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yjs on 2017/11/14.
 */
public interface ArticleDao extends JpaRepository<Article,Long> {

    Article findById(String id);
}
