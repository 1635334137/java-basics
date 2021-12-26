package com.lqj.day03.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * 特点：每次循环查找到最大值
 */
public class BubbleSort {
    public static void main(String[] args) {
        //1.获得数组数据
        int[] arr = {12,10,5,7,2,0,0,1};
        //3.定义一个临时变量存储大的数,因为是局部变量，需要进行初始化
        int temp = 0;
        //11.优化结果
        for (int i = 0; i < arr.length-1; i++) {
            //14.2.最终优化（因为必须得走一次循环才能判断是否是最终排序，所以无法避免一次多余的排序）
            boolean flag = true;
            //2.循环数组
            for (int j=0;j<arr.length-1;j++) {
                //4.对比两个数大小，如果大于后一位则交换位置
                if (arr[j] > arr[j + 1]) {
                    //5.把大的数先存储到临时变量
                    temp = arr[j];
                    //6.小的数位置往前挪（给前一位赋值）
                    arr[j] = arr[j + 1];
                    //7.临时变量赋值给后一个（给后一位赋值）
                    arr[j + 1] = temp;
                    //14.3.最终优化
                    flag = false;
                }
                //8.查看每次循环后的排序结果
                System.out.println(Arrays.toString(arr));
                //9.结果不符合期望结果
//            [10, 12, 5, 7, 2, 0, 0, 1]
//            [10, 5, 12, 7, 2, 0, 0, 1]
//            [10, 5, 7, 12, 2, 0, 0, 1]
//            [10, 5, 7, 2, 12, 0, 0, 1]
//            [10, 5, 7, 2, 0, 12, 0, 1]
//            [10, 5, 7, 2, 0, 0, 12, 1]
//            [10, 5, 7, 2, 0, 0, 1, 12]
                //10.分析后：每次循环只能得到一个最大数，则需要循环数组的长度才能拿到正确的排序结果（每次得一个数，数组有多少个数就循环多少次）
            }
            //14.1.最终优化
            if(flag){
                System.out.println("排序结束！！！");
                break;
            }

            System.out.println("####################");
        }

        //12.分析优化后结果
//        [2, 0, 0, 1, 5, 7, 10, 12]
//        ####################
//        [0, 2, 0, 1, 5, 7, 10, 12]
//        [0, 0, 2, 1, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        ####################
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]
//        [0, 0, 1, 2, 5, 7, 10, 12]

        //13.分析后发现：数组已经排序成功，但是还进行多余的循环排序行为

    }
}

