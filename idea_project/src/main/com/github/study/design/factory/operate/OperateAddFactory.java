package com.github.study.design.factory.operate;

/**
 * Created by pc on 2018/9/6.
 */
public class OperateAddFactory implements IFactory {
    @Override
    public OperateModel createOperate() {
        return new OperateAdd();
    }
}
