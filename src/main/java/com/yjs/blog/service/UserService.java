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

    public boolean login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public User findByName(String username){
        return userDao.findByUsername(username);
    }

}
