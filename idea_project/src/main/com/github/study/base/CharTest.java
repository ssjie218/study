package com.github.study.base;

/**
 * Created by pc on 2018/10/15.
 */
public class CharTest {

    public static void main(String[] args) {
//        char m='a';　　——a。
//
//        char m='a'+'b';　　——Ã。  //char类型相加，提升为int类型，输出对应的字符。注，在CMD.exe用输出结果是问题?，不同的编码输出显示不一样。Eclipse中须改成UTF-8。
//
//        int m='a'+'b';　　 ——195。//195没有超出int范围，直接输出195。
//
//        char m='a'+b;　　——报错。//因为b是一个赋值的变量。
//
//        char m=197;　　——Ã。 //输出字符编码表中对应的字符。
        char m='@'-1;
        System.out.println(m);
//
//        char m='197;　　——报错。//因为有单引号，表示是字符，只允许放单个字符。
//
//        char m='a'+1;　　——b。//提升为int，计算结果98对应的字符是b。
//
//        char m='中'+'国';　　——42282。
//
//        char m='中'+'国'+'国'+'国';　　——报错。int转char有损失。因为结果已经超出char类型的范围。
//
//        int m='中'+'国'+'国'+'国';　　——86820
//
//        char m='中'+1;　　——丮。//1是int，结果提升为int，输出对应的字符。
//
//        char m='中'+"国";　　——报错。String无法转换为char。
//
//        System.out.println('中'+"国");　　——中国。//没有变量附值的过程。String与任何字符用“+”相连，转换为String。

    }
}
