package com.atguigu.demo.edu.service.impl;

import com.atguigu.demo.edu.entity.Video;
import com.atguigu.demo.edu.mapper.VideoMapper;
import com.atguigu.demo.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
