package com.atguigu.demo.edu.service.impl;

import com.atguigu.demo.edu.entity.Course;
import com.atguigu.demo.edu.entity.CourseDescription;
import com.atguigu.demo.edu.entity.vo.CourseInfoForm;
import com.atguigu.demo.edu.entity.vo.CoursePublishVo;
import com.atguigu.demo.edu.mapper.CourseMapper;
import com.atguigu.demo.edu.service.CourseDescriptionService;
import com.atguigu.demo.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        /*保存课程的信息*/
        Course course=new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.insert(course);
        /*保存课程描述的信息*/
        CourseDescription courseDescription=new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescriptionService.save(courseDescription);


        return course.getId();
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }
}
