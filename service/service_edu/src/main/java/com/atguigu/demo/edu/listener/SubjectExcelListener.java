package com.atguigu.demo.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.demo.edu.entity.Subject;
import com.atguigu.demo.edu.entity.excel.ExcelSubjectData;
import com.atguigu.demo.edu.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public SubjectService subjectService;
    public SubjectExcelListener() {
    }
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService=subjectService;
    }
    /*判断一级分类是否重复*/
    private Subject existOneSubject(SubjectService subjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
    /*判断二级分类是否重复*/
    private Subject existTwoSubject(SubjectService subjectService,String
            name,String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData==null){
            throw new GuliException(20001,"Excel表为空");
        }//添加一级分类
   Subject existOneSubject = this.existOneSubject(subjectService,excelSubjectData.getOneSubjectName());
        if(existOneSubject == null) {//没有相同的
            existOneSubject = new Subject();
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值
        String pid = existOneSubject.getId();
        //添加二级分类
       Subject existTwoSubject = this.existTwoSubject(subjectService,excelSubjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new Subject();
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
