package com.school.bbs.controller;

import com.aliyun.oss.OSSErrorCode;
import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.controller.output.FileUploadOutput;
import com.school.bbs.converter.OssConvert;
import com.school.bbs.domain.OssMetaDomain;
import com.school.bbs.service.OssService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.school.bbs.constant.errorCode.OSSErrorCodeEnum.REQUEST_FILE_EMPTY;
import static com.school.bbs.constant.errorCode.OSSErrorCodeEnum.REQUEST_NOT_FILE;

/**
 * @author why
 * @since 2022/10/08/17:32
 */
@Api("文件上传类")
@Slf4j
@RestController
@RequestMapping("file/")
public class FileController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public List<FileUploadOutput> upload(HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.info("oss file upload method start. request uri:{}", uri);

        String clientId = ContextHolder.getLoginContext().getClientId();

        multipartCheck(request);
        MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
        Map<String, MultipartFile> multiFiles = multipartRequest.getFileMap();
        List<FileUploadOutput> results = new ArrayList<>(multiFiles.size());
        for (Map.Entry<String, MultipartFile> entry : multiFiles.entrySet()) {
            OssMetaDomain metaDomain = ossService.upload(clientId, entry.getValue());
            results.add(OssConvert.toFileUpload(metaDomain));
        }
        log.info("oss file upload method end. result:{}", results);
        return results;
    }

    private boolean multipartCheck(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            throw new YyghException(REQUEST_NOT_FILE);
        }

        MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);

        Map<String, MultipartFile> multiFiles = multipartRequest.getFileMap();
        if (multiFiles.isEmpty()) {
            throw new YyghException(REQUEST_FILE_EMPTY);
        }

        return isMultipart;
    }

}
