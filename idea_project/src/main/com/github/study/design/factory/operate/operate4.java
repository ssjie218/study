package com.github.study.design.factory.operate;

/**
 * 工厂模式
 * Created by pc on 2018/9/6.
 */
public class operate4 {
    public static void main(String[] args) {
        IFactory factory=new OperateAddFactory();
        OperateModel model=factory.createOperate();
        model.setNumberA(1D);
        model.setNumberB(2D);
        System.out.println(model.getResult());
    }
}
