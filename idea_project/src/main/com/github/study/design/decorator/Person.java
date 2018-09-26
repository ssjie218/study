package com.github.study.design.decorator;

/**
 * 裝飾模式 給人穿衣服
 * Created by pc on 2018/8/30.
 */
public class Person {
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(name + "穿花衣");
    }

    public static void main(String[] args) {
        Person p = new Person("HHHH");
        Tshirt t = new Tshirt(p);
        Bigtrouser b = new Bigtrouser(t);
        b.show();

    }
}
