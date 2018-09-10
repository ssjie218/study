package com.github.study.design.factory.operate;

/**
 * 计算器  加单工厂模式
 * Created by shen on 2018/7/19.
 */
public class operate3 {

    public static void main(String[] args) {
        Double  a = 33D;
        Double b = 44D;
        String opt = "*";
        OperateModel r =OperateFactory.createOperate(opt);
        r.setNumberA(a);
        r.setNumberB(b);
        System.out.println(r.getResult());
    }


}
