package com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/7/19.
 */
public class OperateAdd extends OperateModel{

    public double getResult(){
        return getNumberA()+getNumberB();
    }
}