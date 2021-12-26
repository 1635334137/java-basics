package com.lqj.day03;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        int[] arr= {23,33,112,42,11,442,53};

        System.out.println(Arrays.toString(arr));

        //数组排序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //二分法查找，找到了返回下标
        int flag = Arrays.binarySearch(arr, 111);
        System.out.println(flag);

    }
}
