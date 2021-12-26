package com.lqj.day03.algorithm;

import java.util.Arrays;

/**
 * 二分法查找：使用二分法前必须先将数组进行排序
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {12,50,11,5,7,3,0,0,0,0,0};
        Arrays.sort(arr);//使用的是快速排序算法
        System.out.println(Arrays.toString(arr));
        int result = myBinarySearch(arr,0);
        System.out.println(result);
    }

    public static int myBinarySearch(int[] arr,int value){
        int low = 0;//起始坐标
        int high = arr.length-1;//终止坐标
        while (low <= high){
            int mid = (low+high)/2;//取中值
            if(value == arr[mid]){
                return mid;
            }
            if(value < arr[mid]){
                high = mid-1;
            }
            if(value > arr[mid]){
                low = mid+1;
            }
        }
        return -1;
    }
}
