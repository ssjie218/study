package com.github.study.base.day01;

/**
 * Created by pc on 2018/11/6.
 */
public class BaseDateTest {

    public static void main(String[] args) {
        char a='1';
        char b='\1';
        char c='\u3403';
        char d='\41';
        char e='감';
        System.out.println(a+0); // '1'的unicode编码 转化为数字相加
        System.out.println(b+0);//转化为整形相加
        System.out.println(b); //找不到对应unicode编码
        System.out.println(c+0);
        System.out.println(c);//unicode编码
        System.out.println(d);//unicode编码
        System.out.println(Integer.toHexString(a));

        System.out.println(e+0);
        System.out.println(Integer.toHexString(49332));
        char f='\uc0b4';
        System.out.println(f);
        System.out.println(e);

        System.out.println(System.getProperties().put("user.language","ko"));
        System.out.println(System.getProperties().get("user.language"));
        System.out.println(e);


    }
}
