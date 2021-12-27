package com.lqj.day04;

import java.util.Arrays;
import java.util.List;

public class StreamDemo2 {
    public static void main(String[] args) {
        //求所有奇数的平方和-外部迭代方式
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = 0;
        for (int n : list) {
            if (n % 2 == 1) {
                int square = n * n;
                sum += square;
            }
        }
        System.out.println(sum);

        //通过stream流内部迭代
        int sum2 = list.stream()
                .filter(n -> n % 2 == 1)
                .map(n -> n * n)
                .reduce(0, Integer::sum);//0为初始值 类::方法
        System.out.println(sum2);

        //外部迭代意味着顺序代码。顺序代码只能由一个线程执行
        //并行处理奇整数的平方和 parallelStream
        int sum3 = list.parallelStream()
                .filter(n -> n % 2 == 1)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println(sum3);

        //有序流：流的排序
        List<Integer> numbers = Arrays.asList(2, 4, 2, 1, 52, 63, 21);
        numbers.stream()
                .filter(n -> n % 2 == 1)
                .sorted()
                .forEach(System.out::println);
    }
}
