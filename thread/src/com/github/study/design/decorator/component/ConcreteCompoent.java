package com.github.study.design.decorator.component;

/**
 * 实例化对象，并且赋予初始职责
 * Created by pc on 2018/8/31.
 */
public class ConcreteCompoent extends Component{

    @Override
    void operation() {
        System.out.println("穿衣服");
    }
}
