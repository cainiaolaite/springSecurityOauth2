package com.hua.cloud.controller;


import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("fdfs")
public class FdfsController {
    @Autowired
    FastFileStorageClient fastFileStorageClient;

    /**
     * upload
     * @param multipartFile
     */
    @PostMapping("upload")
    public String uploadFile(@RequestParam("file")MultipartFile multipartFile)throws IOException {
        String fileName=multipartFile.getOriginalFilename();
        int lastIndex=fileName.lastIndexOf(".");
        String fileExt=fileName.substring(lastIndex+1,fileName.length());
        StorePath group1 = fastFileStorageClient.uploadFile("group1", multipartFile.getInputStream(), multipartFile.getSize(),fileExt );
        return group1.toString();
    }

    /**
     * 下载文件
     * group_name=group1, remote_filename=M00/00/00/wKjchFzvQgiAD3n0AACV8ekMSh0266_big.jpg
     * @return
     */
    @GetMapping("download")
    public String downloadFile(){
        FileInfo fileInfo=fastFileStorageClient.queryFileInfo("group1","M00/00/00/wKjchFzvQgiAD3n0AACV8ekMSh0266_big.jpg");
        return fileInfo.toString();
    }
}
