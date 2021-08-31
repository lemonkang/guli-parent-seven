package com.atguigu.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class InsertExcel {

   private static List<DemoData> getData(){
       List<DemoData> list=new ArrayList<>();
       for (int i = 0; i < 10; i++) {
           DemoData demoData = new DemoData();
           demoData.setSname("老黄鸭"+i);
           demoData.setSno(i);
           list.add(demoData);
       }

       return  list;
   }
    public static void main(String[] args) {
        String fileName = "D:\\11.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("写入方法一").doWrite(getData());
    }
}
