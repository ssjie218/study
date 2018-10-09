package com.github.study.base.binary;

/**
 * 二进制码转化
 * 前缀 0 表示 8 进制，而前缀 0x 代表 16 进制
 *  &0XFF
 * Created by pc on 2018/9/27.
 */
public class Binary {
    public static void main(String[] args) {
        Byte a=-73;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(0xFF));
        // 正数高位补 0相与 得到8位二进制
        System.out.println(Integer.toBinaryString(a&0xFF));

    }
}
