package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by RookieWangZhiWei on 2018/12/23.
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);

}
