package com.school.bbs;

import com.school.bbs.controller.FileController;
import com.school.bbs.domain.OssMetaDomain;
import com.school.bbs.service.OssService;
import com.school.bbs.service.StorageService;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author why
 * @since 2022/10/10/14:32
 */
@SpringBootTest
public class FileTests {
    @Autowired
    private OssService ossService;
    @Autowired
    private StorageService storageService;

    /**
     * 测试 文件上传
     *
     * @throws Exception 异常
     */
    @Test
    public void testFileUpload() throws Exception {
        //生成File文件
        File file = new File("D:\\软件\\WeChat\\file\\WeChat Files\\wxid_xi460i6om2lg22\\FileStorage\\File\\2022-10\\企微\\2022年6月17号周会会议纪要.pdf");

        //File文件转MultipartFile
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        ossService.upload("1", multipartFile);
    }

    /**
     * 测试 文件上传
     *
     * @throws Exception 异常
     */
    @Test
    public void testFileDownLoad() throws Exception {
        OssMetaDomain metaDomain = ossService.exchange(1l);
        File file = storageService.obtain(metaDomain.getUri());
    }

}
