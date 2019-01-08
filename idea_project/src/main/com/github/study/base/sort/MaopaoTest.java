package com.github.study.base.sort;

import java.util.Arrays;


public class MaopaoTest {
    public static void main(String[] args) {
        int num[] = {22, 2, 3, 8, 7,5,19,11,1};
        maopao(num);
        System.out.println(Arrays.toString(num));
        int num2[] = {6, 2, 3, 8, 7,5,19,11,1};
        insertSort(num2);
        System.out.println(Arrays.toString(num2));
        int num3[] = {6, 2, 3, 8, 7,5,19,11,1};
        seqSort(num3);
        System.out.println(Arrays.toString(num3));

    }

    /**
     * 选择排序
     * @param num
     */
    private static void seqSort(int[] num) {
        int len = num.length;
        if (len < 2) {
            return;
        }
        for (int i = 0; i <len-1 ; i++) {
            for (int j = i; j <len-1 ; j++) {
                if (num[j+1] < num[i]) {
                    int tmp = num[j + 1];
                    num[j + 1] = num[i];
                    num[i] = tmp;
                }
            }
        }
    }

    /**
     * 直接插入法排序：
     * 插入排序的比较次数仍然是n的平方，但在一般情况下，它要比冒泡排序快一倍，比选择排序还要快一点；
     *
     * @param num2
     */
    private static void insertSort(int[] num) {
        int len = num.length;
        if (len < 2) {
            return;
        }
        for (int i = 0; i < len - 1; i++) {
            if (num[i + 1] < num[i]) {
                int tmp = num[i + 1];
                num[i + 1] = num[i];
                num[i] = tmp;
                for (int j = i ; j > 0; j--) {
                    if (num[j] < num[j - 1]) {
                        int tmp2 = num[j - 1];
                        num[j - 1] = num[j];
                        num[j] = tmp2;
                    }else{
                        break;
                    }
                }
            }
        }
    }

    /**
     * 这是我们学习接触的第一种排序方法，是一种拿时间换空间的排序方法，它的时间复杂度为O(n^2),每一趟相邻元素的比较都会产生最大值，而这个最大值不会参与下一趟的比较，即每比一趟都会少一个元素，把最大的沉了下去
     * Created by pc on 2018/10/30.
     */
    private static void maopao(int[] num) {
        int len = num.length;
        if (len < 2) {
            return;
        }
        int pwd = 1;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (num[j] > num[j + 1]) {
                    int tmp = num[j + 1];
                    pwd = 0;
                    num[j + 1] = num[j];
                    num[j] = tmp;
                }

            }
            if (pwd == 1) {
                break;
            }
        }
    }
}
