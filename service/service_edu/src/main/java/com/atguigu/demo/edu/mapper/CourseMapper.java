package com.atguigu.demo.edu.mapper;

import com.atguigu.demo.edu.entity.Course;
import com.atguigu.demo.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo getCoursePublishVoById(String id);

}
