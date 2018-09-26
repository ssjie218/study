package com.github.study.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 2018/9/11.
 */
public class RegexSpiderUtil {

    public static String getStr(String rgex, String str) {
        try {
            Pattern pattern = Pattern.compile(rgex);// 匹配的模式
            Matcher m = pattern.matcher(str);
            m.find();
            String s = m.group(1).replaceAll("\"", "").trim();
            return s;
        } catch (Exception E) {
            return "";
        }
    }

    public static String getStr(String rgex, String str, int num) {
        try {
            Pattern pattern = Pattern.compile(rgex);// 匹配的模式
            Matcher m = pattern.matcher(str);
            for (int i = 0; i < num; i++) {
                m.find();
            }
            String s = m.group(1).replaceAll("\"", "").trim();
            return s;
        } catch (Exception E) {
            return "";
        }
    }
}
