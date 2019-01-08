package com.github.study.base.day02;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by pc on 2018/11/7.
 */
public class Day02Test {
    public String name;//共有属性
    private int age;//私有属性
    protected String sex;//受保护的属性
    int a=10;//默认的

    public static void main(String[] args) {
        Day02Test d = new Day02Test();
        System.out.println(d.name);
        System.out.println(d.age);
        System.out.println(d.sex);
        System.out.println(d.a);

//        for(int i=10;i<Integer.MAX_VALUE;i*=10)
//            test(i);
//        Integer[] a = {1, 2, 3,1};
//        List list=Arrays.asList(a);
//        System.out.println(list.size());
//        Set<Integer> set=new HashSet<Integer>(Arrays.asList(a));
//        System.out.println(set.size());
//        ArrayList<Integer> list =new ArrayList<Integer>();
//        for (int i = 0; i < 20; i++) {
//            list.add(i);
//        }
//        System.out.println(list.size());

//
//        ArrayList<String> list =new ArrayList<String>();
//        list.add("333");
//        list.add("44");
//        list.add("55");
//        list.add("11");
//        for(String str:list){
//            if("11".equals(str)){
//                System.out.println("22");
//                list.remove(str);}
//        }
//        System.out.println(list.size());
    }

    static void test(long limit) {
        Random rand = new Random();
        IntStream stream = rand.ints(limit);
        int[] arr = stream.toArray();
        int[] arr1 = Arrays.copyOf(arr, arr.length);

        long t1 = System.currentTimeMillis();
        Arrays.parallelSort(arr);
        long t2 = System.currentTimeMillis();
        Arrays.sort(arr1);
        long t3 = System.currentTimeMillis();
        System.out.println("limit:" + limit + "\t parallelSort: " + (t2 - t1) + "ms\tserialSort: " + (t3 - t2) + "ms");


    }

}
