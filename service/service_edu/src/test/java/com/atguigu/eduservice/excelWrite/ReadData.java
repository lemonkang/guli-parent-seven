package com.atguigu.eduservice.excelWrite;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadData {
    @ExcelProperty(index=0)
    private  int sid;
    @ExcelProperty(index = 1)
    private String sname;

}
