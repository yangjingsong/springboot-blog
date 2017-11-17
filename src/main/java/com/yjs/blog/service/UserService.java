package com.yjs.blog.service;

import com.yjs.blog.dao.UserDao;
import com.yjs.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yjs on 2017/11/14.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);

    }

    public User findByName(String username){
        return userDao.findByUsername(username);
    }

    public User insert(User user){
        return userDao.save(user);
    }

    public User findById(String id){
        return userDao.findById(id);
    }

}
