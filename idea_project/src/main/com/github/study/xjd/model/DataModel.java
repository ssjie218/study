package com.github.study.xjd.model;

/**
 * Created by pc on 2018/11/20.
 */
public class DataModel {
    public BasicInfo basic_info;
    public UserMobileContacts user_mobile_contacts;
    public UserProofMateria user_proof_materia;
    public UserLoanOrders user_loan_orders;

    public void setBasic_info(BasicInfo basic_info) {
        this.basic_info = basic_info;
    }

    public void setUser_mobile_contacts(UserMobileContacts user_mobile_contacts) {
        this.user_mobile_contacts = user_mobile_contacts;
    }

    public void setUser_proof_materia(UserProofMateria user_proof_materia) {
        this.user_proof_materia = user_proof_materia;
    }

    public void setUser_loan_orders(UserLoanOrders user_loan_orders) {
        this.user_loan_orders = user_loan_orders;
    }

    public BasicInfo getBasic_info() {
        return basic_info;
    }

    public UserMobileContacts getUser_mobile_contacts() {
        return user_mobile_contacts;
    }

    public UserProofMateria getUser_proof_materia() {
        return user_proof_materia;
    }

    public UserLoanOrders getUser_loan_orders() {
        return user_loan_orders;
    }
}
