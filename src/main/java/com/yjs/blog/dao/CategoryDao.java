package com.yjs.blog.dao;

import com.yjs.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yjs on 2017/11/15.
 */
public interface CategoryDao extends JpaRepository<Category,Long>{
    Category findByName(String name);
}
