package com.atguigu.eduservice.excelWrite;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<ReadData> {
//    创建list集合封装最终的数据
    List<ReadData> list=new ArrayList<ReadData>();


    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        list.add(readData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取结束的数据"+list);

    }
}
