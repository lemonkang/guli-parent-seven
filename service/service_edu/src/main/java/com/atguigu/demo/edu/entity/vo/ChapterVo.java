package com.atguigu.demo.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "章节信息")
public class ChapterVo implements Serializable {
    private String id;
    private String title;
    private List children=new ArrayList();
}
