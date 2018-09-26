package com.github.study.design.decorator;

/**
 * Created by pc on 2018/8/30.
 */
public class Bigtrouser extends Finery{
    public Bigtrouser(Person person) {
        super(person);
    }

    @Override
    public void show() {
        System.out.println("穿短裤");
        super.show();
    }
}
