package com.yinwei.restfulapi.controller;

import com.yinwei.restfulapi.common.CommonResult;
import com.yinwei.restfulapi.entity.People;
import com.yinwei.restfulapi.exception.CustomsException;
import com.yinwei.restfulapi.service.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
@Slf4j
@RequiredArgsConstructor
/**
 * create by: yinwei
 * description: TODO前端控制器 分配
 * create time: 2021/7/7 13:28
 */
public class PeopleController {

    private final PeopleService peopleService;

    /**
     * create by: yinwei
     * description: TODO 获取全部people信息
     * create time: 2021/7/14 9:27
     *
     * @return CommonResult
     * @Param: null
     */
    @GetMapping
    @PreAuthorize("hasAuthority('people:read')")
    public CommonResult<List<People>> findall() {
        return CommonResult.success(peopleService.findAll());
    }

    /**
     * create by: yinwei
     * description: TODO 获取指定id的people信息
     * create time: 2021/7/14 9:27
     *
     * @return CommonResult
     * @Param: id
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('people:read')")
    public CommonResult<Optional<People>> findById(@PathVariable(value = "id") Long id) throws CustomsException {
        if (!peopleService.findById(id).isPresent()) {
            throw new CustomsException("该id='" + id + "'不存在");
        } else{
            return CommonResult.success(peopleService.findById(id));
        }
    }

    /**
     * create by: yinwei
     * description: TODO 存储people信息
     * create time: 2021/7/14 9:28
     *
     * @return CommonResult
     * @Param: people
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('people:create')")
    public CommonResult<People> save(@RequestBody People people) {
        peopleService.add(people);
        return CommonResult.success(people);
    }

    /**
     * create by: yinwei
     * description: TODO 修改指定id的people信息
     * create time: 2021/7/14 9:29
     *
     * @return Result
     * @Param: id
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('people:update')")
    public CommonResult<People> update(@PathVariable(value = "id") Long id, @RequestBody People people) throws CustomsException {
        if (!peopleService.findById(id).isPresent()) {
            throw new CustomsException("该id='" + id + "'不存在");
        } else {
            people.setId(id);
            peopleService.update(people);
            return CommonResult.success(people);
        }
    }

    /**
     * create by: yinwei
     * description: TODO 删除指定id的people信息
     * create time: 2021/7/14 9:29
     *
     * @return Result
     * @Param: id
     */
    @RequestMapping(value = "delete{id}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('people:delete')")
    public CommonResult<Long> deleteById(@PathVariable Long id) throws CustomsException {
        if (!peopleService.findById(id).isPresent()) {
            throw new CustomsException("该id='" + id + "'不存在");
        } else {
            peopleService.delete(id);
            return CommonResult.success(id);
        }
    }

    /**
     * create by: yinwei
     * description: TODO 模糊查询username
     * create time: 2021/7/16 11:24
     *
     * @return List<People>
     * @Param: username
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('people:read')")
    public CommonResult findByNameLike(String name) throws CustomsException {
        if (peopleService.findAllByNameLike("%" + name + "%").size() == 0) {
            throw new CustomsException("该name='" + name + "'不存在");
        }else{
            List list=peopleService.findAllByNameLike("%"+name+"%");
            return CommonResult.success(list,list.size());
        }
    }
}
