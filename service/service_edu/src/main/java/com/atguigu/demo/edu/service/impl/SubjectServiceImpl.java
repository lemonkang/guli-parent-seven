package com.atguigu.demo.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.demo.edu.entity.Subject;
import com.atguigu.demo.edu.entity.excel.ExcelSubjectData;
import com.atguigu.demo.edu.entity.vo.SubjectNestedVo;
import com.atguigu.demo.edu.entity.vo.SubjectVo;
import com.atguigu.demo.edu.listener.SubjectExcelListener;
import com.atguigu.demo.edu.mapper.SubjectMapper;
import com.atguigu.demo.edu.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-01
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void importSubjectData(MultipartFile file,SubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<SubjectNestedVo> treeSubjectList() {
        /*最终想要得到的数据*/
        List<SubjectNestedVo> FinalList=new ArrayList<>();

        /*获取一级分类的列表*/
        QueryWrapper<Subject> queryWrapperOne=new QueryWrapper<>();
        queryWrapperOne.eq("parent_id",0);
        List<Subject> subjectListOne = baseMapper.selectList(queryWrapperOne);
        for (int i = 0; i < subjectListOne.size(); i++) {
            Subject subject=subjectListOne.get(i);
            SubjectNestedVo subjectNestedVo=new SubjectNestedVo();
            subjectNestedVo.setId(subject.getId());
            subjectNestedVo.setTitle(subject.getTitle());
            FinalList.add(subjectNestedVo);
        }

        /*获取二级分类的列表*/
        QueryWrapper<Subject> queryWrapperTwo=new QueryWrapper<>();
        queryWrapperTwo.ne("parent_id",0);
        List<Subject> subjectListTwo = baseMapper.selectList(queryWrapperTwo);

        for (int i = 0; i < FinalList.size(); i++) {
            SubjectNestedVo subjectNestedVo=FinalList.get(i);
            List<SubjectVo> childrenList=new ArrayList<>();
            for (int j = 0; j < subjectListTwo.size(); j++) {
                Subject subjecttwo=subjectListTwo.get(j);
               if (subjecttwo.getParentId().equals(subjectNestedVo.getId())){
                   SubjectVo subjectVo=new SubjectVo();
                   subjectVo.setId(subjecttwo.getId());
                   subjectVo.setTitle(subjecttwo.getTitle());
                   childrenList.add(subjectVo);
               }
            }
            subjectNestedVo.setChildren(childrenList);
        }
        return FinalList;
    }
}
