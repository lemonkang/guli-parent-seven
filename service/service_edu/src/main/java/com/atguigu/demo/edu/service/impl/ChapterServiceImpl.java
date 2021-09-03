package com.atguigu.demo.edu.service.impl;

import com.atguigu.demo.edu.entity.Chapter;
import com.atguigu.demo.edu.entity.Video;
import com.atguigu.demo.edu.entity.vo.ChapterVo;
import com.atguigu.demo.edu.mapper.ChapterMapper;
import com.atguigu.demo.edu.service.ChapterService;
import com.atguigu.demo.edu.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoService videoService;
    @Override
    public List<ChapterVo> nestedList(String courseId) {
        List<ChapterVo> finalList=new ArrayList<>();

        /*根据courseId在chapter表中查出list数据*/
        QueryWrapper<Chapter> queryWrapperChapter=new QueryWrapper<>();
        queryWrapperChapter.eq("course_id",courseId);
        List<Chapter> listChapters=baseMapper.selectList(queryWrapperChapter);
        /*循环listChapters遍历出想要的ChapterVO数据*/
        for (int i = 0; i <listChapters.size() ; i++) {
            Chapter chapter=listChapters.get(i);
            ChapterVo chapterVo=new ChapterVo();
            chapterVo.setId(chapter.getId());
            chapterVo.setTitle(chapter.getTitle());
            finalList.add(chapterVo);
        }
        /*根据chapterId与courseId获取chapter对应的videolist*/
        for (int i = 0; i < finalList.size(); i++) {
            ChapterVo chapterVo=finalList.get(i);
            QueryWrapper<Video> queryWrapperVideo=new QueryWrapper<>();
            Map parammap=new HashMap();
            parammap.put("course_id",courseId);
            parammap.put("chapter_id",chapterVo.getId());
            queryWrapperVideo.allEq(parammap);
            List<Video> videos = videoService.list(queryWrapperVideo);
            chapterVo.setChildren(videos);
        }
        return finalList;
    }
}
