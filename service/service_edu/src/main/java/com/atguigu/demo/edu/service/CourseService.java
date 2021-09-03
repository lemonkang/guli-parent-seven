package com.atguigu.demo.edu.service;

import com.atguigu.demo.edu.entity.Course;
import com.atguigu.demo.edu.entity.vo.CourseInfoForm;
import com.atguigu.demo.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);
}
