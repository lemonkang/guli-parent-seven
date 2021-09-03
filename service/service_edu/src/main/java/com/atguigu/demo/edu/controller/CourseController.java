package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.demo.edu.entity.vo.CourseInfoForm;
import com.atguigu.demo.edu.entity.vo.CoursePublishVo;
import com.atguigu.demo.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */

@Api(description="课程管理")
@RestController
@RequestMapping("/edu/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("saveCourseInfo")
    public R saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

      String CourseId=  courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("CourseId",CourseId);
    }
    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursePublishInfo/{courseId}")
    public  R getCoursePublishVoById(
            @ApiParam(name = "courseId",value = "课程ID", required = true)
            @PathVariable String courseId
    ){
        CoursePublishVo coursePublishVo=courseService.getCoursePublishVoById(courseId);
         return  R.ok().data("coursePublishVo",coursePublishVo);
    }

}

