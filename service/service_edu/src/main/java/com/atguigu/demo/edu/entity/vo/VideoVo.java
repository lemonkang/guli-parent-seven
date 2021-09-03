package com.atguigu.demo.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "课时信息")
public class VideoVo {
    private String id;
    private String title;
    private boolean free;
}
