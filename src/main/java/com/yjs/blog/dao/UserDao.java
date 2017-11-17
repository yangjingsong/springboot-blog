package com.yjs.blog.dao;

import com.yjs.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yjs on 2017/11/14.
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findById(String id);
}
