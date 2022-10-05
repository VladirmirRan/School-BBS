package com.school.bbs.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 敏感词过滤工具类 TODO:暂时不用
 * @createDate 2022/10/5 14:30
 * @since 1.0.0
 */
@Component
@Slf4j
public class SensitiveFilterUtil {

    /**
     * 敏感词集合
     */
    public static HashMap sensitiveWordMap;

    /**
     * 初始化敏感词库,构建DFA算法模型
     */
    public static void initContext() {
        HashSet<String> set = new HashSet<String>();
        try {
            //获取敏感词文件
            Workbook workbook = new XSSFWorkbook(new FileInputStream("D:\\workspace\\ideaIC\\School-BBS\\src\\main\\resources\\敏感词库.xlsx"));
            // 获取第一个张表
            Sheet sheet = workbook.getSheetAt(0);
            // 获取每行中的字段
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                // 获取行
                Row row = sheet.getRow(j);
                // 略过空行
                if (row == null) {
                    continue;
                } else {
                    if (row.getCell(0) != null) {
                        set.add(row.getCell(0).getStringCellValue());
                    }
                }
            }
            initSensitiveWordMap(set);
        } catch (Exception e) {
            System.out.println("<<<<<<解析敏感词文件报错！");
            e.printStackTrace();
        }
    }

    /**
     * 初始化敏感词库,构建DFA算法模型
     *
     * @param sensitiveWordSet 敏感词库
     */
    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        //初始化敏感词容器,减少扩容操作
        sensitiveWordMap = new HashMap<String, String>(sensitiveWordSet.size());
        Map<Object, Object> temp;
        Map<Object, Object> newWorMap;
        //遍历sensitiveWordSet
        for (String key : sensitiveWordSet) {
            temp = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //库中获取关键字
                Object wordMap = temp.get(keyChar);
                //如果存在该key,直接赋值,用于下一个循环获取
                if (wordMap != null) {
                    temp = (Map) wordMap;
                } else {
                    //不存在则,则构建一个map,同时将isEnd设置为0,因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    temp.put(keyChar, newWorMap);
                    temp = newWorMap;
                }
                //最后一个
                if (i == key.length() - 1) {
                    temp.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * 文本
     *
     * 若包含返回true,否则返回false
     */
//    public static boolean contains(String txt) {
//        boolean flag = false;
//        for (int i = 0; i < txt.length(); i++) {
//            //判断是否包含敏感字符
//            int matchFlag = checkSensitiveWord(txt, i);
//            //大于0存在,返回true
//            if (matchFlag > 0) {
//                flag = true;
//            }
//        }
//        return flag;
//    }

    /**
     * 检查文字中是否包含敏感字符,检查规则如下:
     *
     * @param txt        String
     * @param beginIndex int
     * @return 如果存在, 则返回敏感词字符的长度, 不存在返回0
     */
    private static int checkSensitiveWord(String txt, int beginIndex) {
        //敏感词结束标识位:用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
//        for(String value : nowMap.values()){
//
//        }
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            //存在,则判断是否为最后一个
            if (nowMap != null) {
                //找到相应key,匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环,返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                }
            } else {//不存在,直接返回
                break;
            }
        }
        //长度必须大于等于1,为词
        if (matchFlag < 2 || !flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 获取文字中的敏感词
     *
     * txt文字
     */
//    public static List getSensitiveWord(String txt) {
//        List sensitiveWordList = new ArrayList();
//        for (int i = 0; i < txt.length(); i++) {
//            //判断是否包含敏感字符
//            int length = checkSensitiveWord(txt, i);
//            //存在,加入list中
//            if (length > 0) {
//                sensitiveWordList.add(txt.substring(i, i + length));
//                //减1的原因,是因为for会自增
//                i = i + length - 1;
//            }
//        }
//        return sensitiveWordList;
//    }


    /**
     * context是要校验的内容。返回结果是list，为空说明没有敏感词
     *
     * @param context String
     * @return List
     */
    public static String checkTxt(String context) {
        initContext();
        //包含敏感词返回所有敏感词数据
//        return getSensitiveWord(context);
        return String.valueOf(checkSensitiveWord(context, 0));
    }

}
