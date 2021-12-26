package com.lqj.day03;

import java.util.*;

public class ListALG {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("azhangsan");
        list.add("clisi");
        list.add("bwangwu");

        Collections.sort(list);

        list.forEach(x->{
            System.out.println(x);
        });

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println("o1:"+o1);
                System.out.println("o2:"+o2);
                return 0;
            }
        });


    }
}
