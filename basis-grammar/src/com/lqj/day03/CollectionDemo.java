package com.lqj.day03;

import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        map.put("c","C");
        Set<String> strings = map.keySet();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

        strings.forEach(x->{
            System.out.println(x);
        });
    }
}
