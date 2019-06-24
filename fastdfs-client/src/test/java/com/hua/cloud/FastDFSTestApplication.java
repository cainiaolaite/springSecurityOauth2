package com.hua.cloud;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={FastDFSApplication.class})// 指定启动类
public class FastDFSTestApplication {

  @Autowired
  FastFileStorageClient fastFileStorageClient;
  @Test
  public void download(){
    FileInfo fileInfo=fastFileStorageClient.queryFileInfo("group1","M00/00/00/wKjchFzvQgiAD3n0AACV8ekMSh0266_big.jpg");
    System.out.println(fileInfo.toString());
  }
}
