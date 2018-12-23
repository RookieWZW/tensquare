package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/12/23.
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    public List<Comment> findByArticleid(String articleid);
}
