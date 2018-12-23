package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/12/23.
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        commentDao.save(comment);
    }

    public List<Comment> findByarticleid(String articleid) {

        return commentDao.findByArticleid(articleid);
    }

    public void delete(String commentid) {
         commentDao.deleteById(commentid);
    }
}
