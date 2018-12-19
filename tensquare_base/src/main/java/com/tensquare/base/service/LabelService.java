package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RookieWangZhiWei on 2018/12/19.
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;


    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }


    private Specification<Label> createSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(searchMap.get("labelname")!=null &&
                        !"".equals(searchMap.get("labelname"))){
                    predicateList.add(cb.like(
                            root.get("labelname").as(String.class), "%"+
                                    (String)searchMap.get("labelname")+"%" ) );
                    System.out.println(searchMap.get("labelname"));
                }
                if(searchMap.get("state")!=null &&
                        !"".equals(searchMap.get("state"))){
                    predicateList.add(cb.equal(
                            root.get("state").as(String.class), (String)searchMap.get("state") ) );
                }
                if(searchMap.get("recommend")!=null &&
                        !"".equals(searchMap.get("recommend"))){
                    predicateList.add(cb.equal(
                            root.get("recommend").as(String.class),
                            (String)searchMap.get("recommend") ) );
                }
                return cb.and( predicateList.toArray( new
                        Predicate[predicateList.size()]) );
            }
        };
    }

    public Page<Label> findSearch(Map searchMap,int page,int size){
        Specification specification= createSpecification(searchMap);
        Pageable pageRequest=PageRequest.of(page-1,size);
        return labelDao.findAll( specification ,pageRequest);

    }
}
