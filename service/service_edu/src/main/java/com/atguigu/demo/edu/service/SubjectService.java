package com.atguigu.demo.edu.service;

import com.atguigu.demo.edu.entity.Subject;
import com.atguigu.demo.edu.entity.vo.SubjectNestedVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-01
 */
public interface SubjectService extends IService<Subject> {

    void importSubjectData(MultipartFile file, SubjectService subjectService);

    List<SubjectNestedVo> treeSubjectList();
}
