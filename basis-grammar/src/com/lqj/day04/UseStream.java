package com.lqj.day04;

import java.util.Optional;
import java.util.stream.Stream;

public class UseStream {

    //判断是否是奇数
    public static boolean isOdd(long number) {
        if (number % 2 == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        Stream.iterate(2L, n -> n + 2) 这里永远也得不到奇数，所以使用流时要注意无限循环问题
//                .filter(UseStream::isOdd)
//                .limit(5) 注意：意思是最多得到多少个数，如果得不到则无限循环下去
//                .forEach(System.out::println);
        Stream.iterate(2L, n -> n + 1)
                .filter(UseStream::isOdd)
                .limit(5)
                .forEach(System.out::println);

        //以下代码使用skip跳过前100个奇数
        Stream.iterate(2L, n -> n + 1)
                .filter(UseStream::isOdd)
                .skip(100)// 跳过流的前n个元素
                .limit(5)
                .forEach(System.out::println);

        //使用Supplier来生成无限顺序无序流
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);


        /*Java 8引入了一个java.util.Optional类来优雅地处理NullPointerException。
        Optional是可以包含或不包含非空值的非空值的包装器。
        可能返回null的方法应返回Optional，而不是null。*/
        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5).reduce(Integer::max);

        if (max.isPresent()) {
            System.out.println("max = " + max.get());
        } else {
            System.out.println("max is not  defined.");
        }

        max = Stream.<Integer>empty().reduce(Integer::max);
        if (max.isPresent()) {
            System.out.println("max = " + max.get());
        } else {
            System.out.println("max is not  defined.");
        }
    }


}
