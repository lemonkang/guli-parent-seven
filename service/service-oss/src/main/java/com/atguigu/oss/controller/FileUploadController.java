package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class FileUploadController {
    @Autowired
    private FileService fileService;
    @ApiOperation("文件上传")
    @PostMapping("upload")
    public  R upload(
            @ApiParam(name = "file",value = "文件")
            @RequestParam("file")MultipartFile file
            ){
        String uploadUrl=null;
        try {
      uploadUrl = fileService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return R.ok().message("图片上传成功").data("url",uploadUrl);
    }


}
