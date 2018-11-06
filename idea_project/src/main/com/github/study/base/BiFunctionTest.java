package com.github.study.base;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class BiFunctionTest {

    public static void main(String[] args) {
        BiFunctionTest function = new BiFunctionTest();
        List<FucntionPerson> lists = Arrays.asList(new FucntionPerson("zhangsan",20)
                ,new FucntionPerson("lisi",15),new FucntionPerson("wangwu",30));
        List<FucntionPerson> listSource = function.getListByUsername("lisi",lists);
        /** 下面的遍历我们也用java8里面的ForEach吧，不然，写着太麻烦了*/
        listSource.forEach(person -> System.out.println(person.getUsername()+", " + person.getAge()));

    }

    /**
     *
     * @param userName  待匹配参数
     * @param lists      源数据
     * @return           与之匹配数据
     *  这个方法里面用到了Java1.8中的BiFunction函数，但是，并没有简化多少
     */
    public List<FucntionPerson> getListByUsername(String userName, List<FucntionPerson> lists) {

        BiFunction<String,List<FucntionPerson>,List<FucntionPerson>> biFunction = new BiFunction<String, List<FucntionPerson>, List<FucntionPerson>>() {
            @Override
            public List<FucntionPerson> apply(String s, List<FucntionPerson> personList) {

                return personList.stream().filter(person -> person.getUsername().equals(s)).collect(Collectors.toList());
            }
        };
        return biFunction.apply(userName,lists);
    }
}
