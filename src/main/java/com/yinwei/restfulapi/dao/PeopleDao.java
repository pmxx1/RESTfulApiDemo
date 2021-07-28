package com.yinwei.restfulapi.dao;

import com.yinwei.restfulapi.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface PeopleDao extends JpaRepository<People,String> {
     /**
      * create by: yinwei
      * description: TODO 创建JpaRepository中没有的findAllById、deleteById、findAllByNameLike方法
      * create time: 2021/7/14 9:30
      *
       * @Param: id
      * @return List<People>
      */

     Optional<People> findById(Long id);

     void deleteById(Long id);

     List<People> findAllByNameLike(String name);
}
