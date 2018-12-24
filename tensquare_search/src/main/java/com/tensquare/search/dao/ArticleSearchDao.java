package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



/**
 * Created by RookieWangZhiWei on 2018/12/24.
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {

    public Page<Article> findByTitleOrContentLike(String titile, String content, Pageable pageable);
}

