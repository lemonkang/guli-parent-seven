package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.demo.edu.entity.vo.SubjectNestedVo;
import com.atguigu.demo.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-01
 */
@Api(description = "课程分类")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    SubjectService subjectService;


    @PostMapping("addSubject")
    @ApiOperation("批量导入课程")
    public R addSubject(MultipartFile file){
        subjectService.importSubjectData(file,subjectService);
        return R.ok();
    }
    @ApiOperation("课程的树形结构")
    @GetMapping ("treeSubject")
    public R treeSubject(){
       List<SubjectNestedVo> list= subjectService.treeSubjectList();
       return R.ok().data("item",list);
    }

}

