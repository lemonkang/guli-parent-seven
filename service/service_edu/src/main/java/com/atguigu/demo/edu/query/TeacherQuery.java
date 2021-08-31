package com.atguigu.demo.edu.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "水平")
    private Integer level;
    @ApiModelProperty(value = "开始时间",example = "2019-11-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "结束时间",example = "2019-12-01 10:10:10")
    private String end;
}
