package com.java.basis;

/**
 * 基本数据类型
 *
 * @author Mr.Lan
 * @create: 2022-01-13 14:50
 */
public class DataTypeDemo {
    public static void main(String[] args) {
        //四种整型
        System.out.println("Byte类型的大小："+Byte.SIZE);
        System.out.println("最大值："+Byte.MAX_VALUE);
        System.out.println("最小值："+Byte.MIN_VALUE);
        System.out.println("Short类型的大小："+Short.SIZE);
        System.out.println("最大值："+Short.MAX_VALUE);
        System.out.println("最小值："+Short.MIN_VALUE);
        System.out.println("Integer类型的大小："+Integer.SIZE);
        System.out.println("最大值："+Integer.MAX_VALUE);
        System.out.println("最小值："+Integer.MIN_VALUE);
        System.out.println("Long类型的大小："+Long.SIZE);
        System.out.println("最大值："+Long.MAX_VALUE);
        System.out.println("最小值："+Long.MIN_VALUE);

        //两种浮点型
        System.out.println("Float类型的大小："+Float.SIZE);
        System.out.println("最大值："+Float.MAX_VALUE);
        System.out.println("最小值："+Float.MIN_VALUE);
        System.out.println("Double类型的大小："+Double.SIZE);
        System.out.println("最大值："+Double.MAX_VALUE);
        System.out.println("最小值："+Double.MIN_VALUE);

        //一种字符型
        System.out.println("Character类型的大小："+Character.SIZE);
        System.out.println("最大值："+(int)Character.MAX_VALUE);
        System.out.println("最小值："+(int)Character.MIN_VALUE);

        //一种布尔型
        System.out.println(Boolean.TRUE);
        System.out.println(Boolean.FALSE);
    }
}
