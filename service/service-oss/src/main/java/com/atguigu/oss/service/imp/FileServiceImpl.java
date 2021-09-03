package com.atguigu.oss.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.FileService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
//        获取阿里云存储的相关变量
        String endPoint= ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
        // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);


            //        获取上传文件的输入流
            InputStream inputStream=file.getInputStream();
            //获取文件名称
            String fileName=file.getOriginalFilename();
//            生成随机数
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName=uuid+fileName;
            /*创建格式化对象*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            /*创建日期对象*/
            Date d=new Date();
            String s=simpleDateFormat.format(d);
//            上传
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
//            获取URL地址.00
            String uploadUrl="http://" + bucketName + "." + endPoint + "/" + fileName;
            return  uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
