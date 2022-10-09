package com.school.bbs.converter;

import com.school.bbs.controller.output.FileUploadOutput;
import com.school.bbs.domain.OssMetaDomain;

/**
 * @author why
 * @since 2022/10/09/10:06
 */
public class OssConvert {
    public static FileUploadOutput toFileUpload(OssMetaDomain metaDomain) {
        FileUploadOutput output = new FileUploadOutput();
        output.setFileId(metaDomain.getId());
        return output;
    }
}
