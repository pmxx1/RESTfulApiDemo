package com.yinwei.restfulapi.controller;

import com.yinwei.restfulapi.entity.People;
import com.yinwei.restfulapi.entity.Result;
import com.yinwei.restfulapi.entity.StatusCode;
import com.yinwei.restfulapi.exception.CustomsException;
import com.yinwei.restfulapi.service.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
/**
 * create by: yinwei
 * description: TODO前端控制器 分配
 * create time: 2021/7/7 13:28
 * 
  * @Param: null
 * @return 
 */
public class PeopleController {

    private  final PeopleService peopleService;
    /**
     * create by: yinwei
     * description: TODO 调用service层，注入bean
     * create time: 2021/7/14 9:25
     */
//    @Autowired
//    public PeopleController(PeopleService peopleService) {
  //      this.peopleService = peopleService;
    //}
    /**
     * create by: yinwei
     * description: TODO 获取全部people信息
     * create time: 2021/7/14 9:27
     *
      * @Param: null
     * @return Result
     */
    @GetMapping
    public Result findall(){
        return new Result(true, StatusCode.OK,"查询成功",peopleService.findAll());
    }
    /**
    * create by: yinwei
    * description: TODO 获取指定id的people信息
    * create time: 2021/7/14 9:27
    *
    * @Param: id
    * @return Result
    */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id")String id) throws CustomsException {
        if(!peopleService.findById(id).isPresent()){
            log.error("该id='"+id+"'不存在);");
            throw new CustomsException("该id='"+id+"'不存在");
        }else {
            return new Result(true,StatusCode.OK,"查询成功",peopleService.findById(id));
        }
    }
    /**
     * create by: yinwei
     * description: TODO 存储people信息
     * create time: 2021/7/14 9:28
     *
      * @Param: people
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody People people){
        peopleService.add(people);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    /**
     * create by: yinwei
     * description: TODO 修改指定id的people信息
     * create time: 2021/7/14 9:29
     *
      * @Param: id
     * @return Result
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody People people) throws CustomsException {
        if(!peopleService.findById(id).isPresent()){
            log.error("该id='"+id+"'不存在");
            throw new CustomsException("该id='"+id+"'不存在");
        }
        else {
            people.setId(id);
            peopleService.update(people);
            return new Result(true, StatusCode.OK, "修改成功");
        }
    }

    /**
     * create by: yinwei
     * description: TODO 删除指定id的people信息
     * create time: 2021/7/14 9:29
     *
      * @Param: id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable String id) throws CustomsException {
        if(!peopleService.findById(id).isPresent()) {
            log.error("该id='"+id+"'不存在);");
            throw new CustomsException("该id='"+id+"'不存在");
        }else{
            peopleService.delete(id);
            return new Result(true, StatusCode.OK, "删除成功");
        }
    }
    /**
     * create by: yinwei
     * description: TODO 模糊查询username
     * create time: 2021/7/16 11:24
      * @Param: username
     * @return List<People>
     */
    @GetMapping(value = "/name{name}")
    public Result findByNameLike(String name) throws CustomsException {
        if (peopleService.findByNameLike(name).size()==0) {
            log.error("该username中'"+name+"'不存在);");
            throw new CustomsException("该name='"+name+"'不存在");
        } else {
            return new Result(true, StatusCode.OK, "查询成功", peopleService.findByNameLike("%" + name + "%"));
        }
    }
}
