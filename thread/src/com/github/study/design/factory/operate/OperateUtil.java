package com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/7/19.
 */
public class OperateUtil {
    static int getOptResult(int a, int b, String opt) {
        Integer r = 0;
        switch (opt) {
            case "+":
                r = a + b;
                break;
            case "-":
                r = a - b;
                break;
            case "*":
                r = a * b;
                break;
            case "/":
                if (b == 0) {
                    System.out.println("除数不能为0");
                }
                r = a / b;
                break;
        }
        return r;
    }
}
