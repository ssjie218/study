package com.github.study.base;

/**
 * Created by pc on 2018/10/25.
 */
public class FucntionPerson {
    private String username;
    private Integer age;

    public FucntionPerson(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
