package com.yjs.blog.dao;

import com.yjs.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yjs on 2017/11/14.
 */
public interface ArticleDao extends JpaRepository<Article,String> {

    Article findById(String id);

    List<Article> findByAuthorId(String authorId);
}
