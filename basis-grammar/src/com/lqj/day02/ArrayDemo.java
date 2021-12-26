package com.lqj.day02;

import java.util.ArrayList;

public class ArrayDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int size = arrayList.size();//获取数组大小

        arrayList.add(20);//为数组添加元素
        arrayList.add(30);

        System.out.println(size = arrayList.size());

        arrayList.remove(1);

        for (int i : arrayList) {
            System.out.println(i);
        }

        arrayList.clear();//清空数组

        ArrayList<String> stringArray = new ArrayList<>();
        stringArray.add("zhangsan");
        stringArray.add("lis");

        String[] nameStr = new String[stringArray.size()];
        nameStr = stringArray.toArray(nameStr);

        for (String s : nameStr) {
            System.out.println(s+" ");
        }
    }
}
