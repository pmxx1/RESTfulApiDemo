package com.yinwei.restfulapi.service;

import com.yinwei.restfulapi.dao.PeopleDao;
import com.yinwei.restfulapi.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * create by: yinwei
 * description: TODO service层
 * create time: 2021/7/7 13:40
 */
@Transactional
@Service
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;



    /**
     * create by: yinwei
     * description: TODO 查询所有people信息
     * create time: 2021/7/14 9:35
     *
      * @Param: null
     * @return List<people>
     */
    public List<People> findAll(){
        return peopleDao.findAll();
    }
    /**
     * create by: yinwei
     * description: TODO 查询对应id的people信息
     * create time: 2021/7/14 9:35
     *
      * @Param: id
     * @return List<people>
     */
    public Optional<People> findById(Long id){return peopleDao.findById(id);}
    /**
     * create by: yinwei
     * description: TODO 添加people信息
     * create time: 2021/7/14 9:37
     *
      * @Param: people
     * @return people
     */
    public People add(People people){
        return  peopleDao.save(people);
    }
    /**
     * create by: yinwei
     * description: TODO 修改people信息
     * create time: 2021/7/14 9:37
     *
     * @Param: people
     * @return people
     */
    public People update(People people){
        return peopleDao.save(people);
    }
    /**
     * create by: yinwei
     * description: TODO 删除对应id的people信息
     * create time: 2021/7/14 9:37
     *
     * @Param: id
     * @return null
     */
    public void delete(Long id){
        peopleDao.deleteById(id);
    }

    public List<People> findAllByNameLike(String name) {
        return peopleDao.findAllByNameLike(name);
    }
}
