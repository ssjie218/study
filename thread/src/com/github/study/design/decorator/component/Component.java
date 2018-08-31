package com.github.study.design.decorator.component;

/**
 * 定义对象接口，定义给对象添加职责方法
 * Created by pc on 2018/8/31.
 */
abstract class Component {
    abstract void operation();

    public static void main(String[] args) {
        ConcreteCompoent c=new ConcreteCompoent();
        ConcreteDecoratorA a=new ConcreteDecoratorA();
        ConcreteDecoratorB b=new ConcreteDecoratorB();
        a.setComponent(c);
        b.setComponent(a);
        b.operation();
    }
}
