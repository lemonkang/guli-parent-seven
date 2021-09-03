package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.demo.edu.entity.vo.ChapterVo;
import com.atguigu.demo.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
@Api(description="课程章节管理")
@CrossOrigin  //跨域
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @GetMapping("nestedList/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId",value = "课程ID")
            @PathVariable String courseId
    ){
       List<ChapterVo> list= chapterService.nestedList(courseId);


        return R.ok().data("ChapterList",list);
    }

}

