package com.yjs.blog.dao;

import com.yjs.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yjs on 2017/11/27.
 */
public interface CommentDao extends JpaRepository<Comment,String> {
    List<Comment> findByArticleId(String articleId, Sort sort);

}
