package com.github.study.cash;

/**
 * Created by pc on 2018/11/2.
 */
public class XjdDemo {

    public static void main(String[] args) {
        // 借款金额
        double jkFee=2000;
        // 费用比例
        double feeRate=0.25;
        // 用户成本
        double userFee=150;
        // 催收成本
        double csFee=150;
        //  借款金额*费用比例-用户成本-逾期成本>0  收益
        // 逾期成本=借款金额*费用比例-用户成本
        double yqFee=jkFee*feeRate-userFee;
        System.out.println(yqFee);
        System.out.println(1-yqFee/jkFee);
    }
}
