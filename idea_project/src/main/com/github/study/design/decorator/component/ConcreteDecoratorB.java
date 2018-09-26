package com.github.study.design.decorator.component;

/**
 * Created by pc on 2018/8/31.
 */
public class ConcreteDecoratorB extends Decorator{
    @Override
    void operation() {
        System.out.println("先穿B");
        super.operation();
    }
}
