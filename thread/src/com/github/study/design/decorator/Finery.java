package com.github.study.design.decorator;

/**
 * Created by pc on 2018/8/30.
 */
abstract class Finery extends Person {
    private Person person;
    public Finery() {
    }

    public Finery(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        person.show();
    }
}
