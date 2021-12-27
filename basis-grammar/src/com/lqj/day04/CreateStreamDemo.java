package com.lqj.day04;

import java.util.stream.Stream;

public class CreateStreamDemo {
    public static void main(String[] args) {
        //从值创建流
        Stream<String> stream = Stream.of("abc");//参数是个T泛型
        stream.forEach(System.out::println);

        //流构建器
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> stream2 = Stream.<String>builder()
                .add("XML")
                .add("JAVA")
                .build();
        stream2.forEach(System.out::println);

        //Java函数流
        //两个参数:种子和函数。 limit(long maxSize)操作是产生另一个流的中间操作，指定有多少个中间操作
        Stream<Long> stream3 = Stream.iterate(1L, n -> n + 1)
                .limit(10);
        stream3.forEach(System.out::print);
    }
}
