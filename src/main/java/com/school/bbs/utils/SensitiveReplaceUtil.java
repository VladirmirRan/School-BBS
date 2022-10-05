package com.school.bbs.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 敏感词替换工具类
 * @createDate 2022/10/5 16:41
 * @since 1.0.0
 */
@Component
@Slf4j
public class SensitiveReplaceUtil {

    /**
     * 读取指定路径敏感词库文件
     * @return
     */
    public static ArrayList<String> readTxt() {
        ArrayList<String> sensitiveWords = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try {
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\ideaIC\\School-BBS\\src\\main\\resources\\敏感词库.txt"));
            String line = null;
            //使用readLine方法，一次读一行
            while ((line = br.readLine()) != null) {
                sensitiveWords.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWords;
    }

    public static String replaceContent(String content){
        ArrayList<String> sensitiveWords = readTxt();
        //1.遍历str中的内容（目的:判断传入的内容里面有没有str中的内容）
        for (String word : sensitiveWords) {
            //2.判断target中有没有敏感词
            if (content.contains(word)){
                //3.判断敏感词的个数 -> 一个敏感词对应一个*...
                int len = word.length();
                //4.定义一个敏感词替换字符
                StringBuilder replace = new StringBuilder("*");
                //注意：我们定义的replace已经是*
                //如果输入的是一个字符，直接替换即可，不用进下面的循环
                for (int i = 0; i < len-1; i++) {
                    //5.确定需要替换敏感词的长度，决定*的个数
                    replace.append("*");
                }
                //6.实现敏感词的替换
                content = content.replace(word, replace.toString());
            }
        }
        return content;
    }



}
