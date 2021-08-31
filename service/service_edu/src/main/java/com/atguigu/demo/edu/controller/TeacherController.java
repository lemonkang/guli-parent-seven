package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.demo.edu.entity.Teacher;
import com.atguigu.demo.edu.query.TeacherQuery;
import com.atguigu.demo.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-25
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/user")
public class TeacherController {
//            http://localhost:8001/edu/teacher/finAll
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取所有老师")
    @GetMapping("findAll")
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("list",list);
    }
    @ApiOperation("新增老师")
    @GetMapping("inster")
    public R insert(){
        Teacher teacher = new Teacher();
        teacher.setName("龟千岁");
        teacher.setIntro("123");
        teacher.setLevel(3);
        teacher.setSort(21);
        boolean save = teacherService.save(teacher);
        return R.ok();
    }
    @ApiOperation("逻辑删除老师")
    @DeleteMapping("{id}")
    public R remove(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error();
    }
    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public R pagelimit(
           @ApiParam(name = "page",value = "当前页",required = true)
            @PathVariable long page,
           @ApiParam(name = "limit",value = "每页返回的结果数")
           @PathVariable long limit
    ){
        Page<Teacher> pageparam=new Page<>(page,limit);
        teacherService.page(pageparam,null);
        List<Teacher> records = pageparam.getRecords();
        long total = pageparam.getTotal();
        System.out.println(pageparam+"参数");
        return R.ok().data("total", total).data("rows", records);
    }
    @ApiOperation("带条件的分页查询")
    @PostMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page",value = "当前页",required = true)
            @PathVariable long page,
            @ApiParam(name = "limit",value = "每页返回的结果数")
            @PathVariable long limit,
         @RequestBody(required = false)  TeacherQuery teacherQuery
    ){
        Page<Teacher> pageparam=new Page<>(page,limit);
        QueryWrapper<Teacher> queryWrapper=new QueryWrapper();
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end=teacherQuery.getEnd();

        if (!StringUtils.isNullOrEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (level !=null){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isNullOrEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isNullOrEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        teacherService.page(pageparam,queryWrapper);
        List<Teacher> records = pageparam.getRecords();
        long total = pageparam.getTotal();
        System.out.println(pageparam+"参数");
        return R.ok().data("total", total).data("rows", records);

    }
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }
    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }
    @ApiOperation("异常")
    @PostMapping("error")
    public  void error(){
        int a=3/0;
    }
}

