package com.atguigu.demo.edu.service;

import com.atguigu.demo.edu.entity.Chapter;
import com.atguigu.demo.edu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);
}
