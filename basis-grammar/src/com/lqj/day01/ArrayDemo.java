package com.lqj.day01;

import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] intArrays = new int[5];
        for (int i=0;i<intArrays.length;i++){
            intArrays[i] = i;
        }
        for (int j:intArrays){
            System.out.println("["+j+"]="+intArrays[j]);
        }
        System.out.println("-----------------------------------");

        double[] doubleArrays = {22.11,22.56,33.43};
        for (double doubleArray : doubleArrays) {
            System.out.println(doubleArray);
        }

        int[] arr = new int[10];//数组属于引用数据类型，所以在数组使用之前一定要开辟空间（实例化）
        arr[0] = 10;
        arr[1] = 7;
        arr[2] = 5;
        arr[3] = 50;
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
