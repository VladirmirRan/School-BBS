package com.school.bbs.controller;

import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.output.FileUploadOutput;
import com.school.bbs.converter.OssConvert;
import com.school.bbs.domain.OssMetaDomain;
import com.school.bbs.service.OssService;
import com.school.bbs.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
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
@RequestMapping("file")
public class FileController {

    @Autowired
    private OssService ossService;

    @Autowired
    private StorageService storage;

    @ApiResult
    @PostMapping("upload")
    public List<FileUploadOutput> upload(HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.info("oss file upload method start. request uri:{}", uri);
        String clientId = ContextHolder.getLoginContext().getClientId();
        //String clientId = "5c1e1b0f31c34db68c6b4f91279e0263";
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

    @ApiOperation("文件下载")
    @GetMapping(path = "download")
    public @ResponseBody
    ResponseEntity<byte[]> download(@RequestParam(name = "fileId") Long fileId) throws IOException {
        OssMetaDomain metaDomain = ossService.exchange(fileId);
        return download(metaDomain);
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

    private ResponseEntity<byte[]> download(OssMetaDomain metaDomain) throws IOException {
        File file = storage.obtain(metaDomain.getUri());
        MediaType mediaType = MediaType.parseMediaType(metaDomain.getMediaType());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(metaDomain.getName(), "UTF-8"));
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

}
