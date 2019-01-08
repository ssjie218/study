package com.github.study.xjd.model;

import java.util.List;

/**
 * Created by pc on 2018/11/20.
 */
public class BasicInfo {
    public String register_time;
    public List<UserContact> user_contact;

    public String getRegister_time() {
        return register_time;
    }

    public List<UserContact> getUser_contact() {
        return user_contact;
    }
}
