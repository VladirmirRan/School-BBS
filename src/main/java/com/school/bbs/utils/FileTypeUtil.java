package com.school.bbs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author why
 * @since 2022/10/09/11:14
 */
public class FileTypeUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileTypeUtil.class);
    private static final int HASE_MAP_SIZE = 16;
    private static final String ZIP_TYPE = "zip";
    private static final String C3_TYPE = "3c";
    private static final String JPG_CODE = "ffd8ff";
    private static final String JPEG_TYPE = "jpeg";
    private static final String JPG_TYPE = "jpg";
    private static final String BMP_TYPE = "bmp";
    private static final String PDF_TYPE = "pdf";
    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>(HASE_MAP_SIZE);


    private FileTypeUtil() {
    }

    static {
        //初始化文件类型信息
        getAllFileType();
    }

    private static void getAllFileType() {
        //JPEG (jpg)
        FILE_TYPE_MAP.put("ffd8ff", "jpg");
        //PNG
        FILE_TYPE_MAP.put("89504e", "png");
        //pdf
        FILE_TYPE_MAP.put("255044", "pdf");
        //HTML转PDF
        FILE_TYPE_MAP.put("3c", "pdf");
        //zip
        FILE_TYPE_MAP.put("504b", "zip");
        //rar
        FILE_TYPE_MAP.put("526172", "rar");
        //bmp
        FILE_TYPE_MAP.put("424d", "bmp");
        //HTML (html)
        FILE_TYPE_MAP.put("3c2144", "html");
        FILE_TYPE_MAP.put("3c6874", "html");
        FILE_TYPE_MAP.put("3c4854", "html");
        FILE_TYPE_MAP.put("68746d", "html");

        //image/x-icon
        FILE_TYPE_MAP.put("000001", "ico");
        //text/plain
        FILE_TYPE_MAP.put("002a2a", "plain");
        //image/webp
        FILE_TYPE_MAP.put("524946", "webp");
    }


    /**
     * 得到上传文件的文件头
     *
     * @param src src
     * @return 文件头
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /**
     * @param file InputStream
     * @return 文件头信息
     * @author guoxk
     * <p>
     * 方法描述：根据文件流获取文件头信息
     */
    public static String getFileHeader(InputStream file, String fileSuffix) {
        String value = null;
        try {
            byte[] b = new byte[3];
            //特殊格式
            if (ZIP_TYPE.equalsIgnoreCase(fileSuffix) || BMP_TYPE.equalsIgnoreCase(fileSuffix)) {
                b = new byte[2];
            }
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            file.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            logger.info("根据文件流获取文件头信息异常Exception", e);
        } finally {
            if (null != file) {
                try {
                    file.close();
                } catch (IOException e) {
                    logger.info("根据文件流获取文件头信息异常IOException", e);
                }
            }
        }
        logger.info("根据文件流获取文件 file suffix {} 头编号 file code :{}", fileSuffix, value);
        if (StringUtils.hasText(value)) {
            String substring = value.substring(0, 2);
            if (PDF_TYPE.equalsIgnoreCase(fileSuffix) && C3_TYPE.equalsIgnoreCase(substring)) {
                logger.info("根据文件流获取文件头 file type:{},code :{}", PDF_TYPE, C3_TYPE);
                return C3_TYPE;
            }

            return value.toLowerCase();
        }
        return value;
    }

    private static String getFileType(InputStream file, String fileSuffix) {
        String fileCode = getFileHeader(file, fileSuffix);
        String suffix = null;
        for (String key : FILE_TYPE_MAP.keySet()) {
            //比较前几位是否相同就可以判断文件格式（相同格式文件文件头后面几位会有所变化）
            if (key.equalsIgnoreCase(fileCode)) {
                //如果key值是 ffd8ff 并且 后缀是 jpeg，jpg 直接返回后缀
                if (JPG_CODE.equals(fileCode) && isJpgWith(fileSuffix)) {
                    suffix = fileSuffix;
                    break;
                }
                suffix = FILE_TYPE_MAP.get(key);
                break;
            }
        }
        return suffix;
    }

    private static boolean isJpgWith(String fileSuffix) {
        return JPEG_TYPE.equalsIgnoreCase(fileSuffix) || JPG_TYPE.equalsIgnoreCase(fileSuffix);
    }

    /**
     * 获取文件类型
     *
     * @param file
     * @return
     */
    public static boolean fileType(InputStream file, String fileSuffix) {
        String res = getFileType(file, fileSuffix);
        logger.info("file文件后缀为res:{},原文件后缀fileSuffix:{}", res, fileSuffix);
        if (StringUtils.hasText(res) && fileSuffix.equalsIgnoreCase(res)) {
            return true;
        }
        return false;
    }


}
