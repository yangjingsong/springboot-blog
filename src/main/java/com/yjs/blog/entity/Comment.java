package com.yjs.blog.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yjs on 2017/11/27.
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "comment")
    private String comment;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
