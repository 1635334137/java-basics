[TOC]



# 一、Stream概要与使用场景

### stream概要

​	java8中的stream与InputStream和OutputStream是完全不同的概念，stream是用于对集合迭代器的增强，使之能够完成更高效的聚合操作（过滤、排序、统计分组）或者大批量数据操作。此外stream与lambda表达式结合后编码效率大大提高，可读性更强。

##### stream产生的背景

遍历在传统的javaEE项目中数据源比较单一而且集中，但现在的互联网项目数据源多样化有：关系数据库、NoSQL、Redis、mongoDB、ES等。这时需要我们从各数据源中汇聚数据并进行统计。这在stream出现之前只能通过遍历实现，非常繁琐。

###### 场景一：跨库join的问题

在分布式场景下join不能使用，服务端实现其流程通过迭代方法非常繁琐。

### 使用stream流形式处理

![image-20211227180312769](C:\Users\lanzong\AppData\Roaming\Typora\typora-user-images\image-20211227180312769.png)



### Stream执行机制解密

##### **原理：责任链模式**

##### 流的操作特性

1.stream不存储数据

2.stream不改变源数据

3.stream不可重复使用

![image-20211227181420883](C:\Users\lanzong\AppData\Roaming\Typora\typora-user-images\image-20211227181420883.png)

### Stream的使用

##### stream流的生成与不可重复用

![image-20211227182019528](C:\Users\lanzong\AppData\Roaming\Typora\typora-user-images\image-20211227182019528.png)

代码：

```java
public class StreamDemo {
    static class Apple{
        int id;
        String color;

        public Apple(int id, String color) {
            this.id = id;
            this.color = color;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
        public void colors(){
            System.out.println(this.color);
        }
    }
    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(1,"red"));
        list.add(new Apple(2,"green"));
        Stream<Apple> stream1 = list.stream();
        Stream<Apple> stream2 = stream1.filter(a -> a.getColor().equals("red"));
        stream1.forEach(apple -> apple.colors());
        //报错
    }
}
```

##### 流的操作类型

中间操作：调用中间操作方法会返回一个新的流，执行多个连续的中间操作就组成了管道

终值操作：调用该方法之后，将执行之前所有的中间操作，获得返回结果，结束对流的使用

**注意：流的操作不是一次传递所有数据，而是一个一个的处理**

![image-20211227184758914](C:\Users\lanzong\AppData\Roaming\Typora\typora-user-images\image-20211227184758914.png)



##### 流的常用方法

| 方法     | 描述                                          | 操作类型 |
| -------- | --------------------------------------------- | -------- |
| filter   | 接收一个Boolean表达式来过滤元素               | 中间操作 |
| map      | 将流中元素1：1映射成另外一个元素              | 中间操作 |
| forEach  | 遍历流中所有元素                              | 终值操作 |
| sorted   | 排序                                          | 中间操作 |
| peek     | 遍历流中所有元素，如forEach不同在于不会结束流 | 中间操作 |
| toArray  | 将流中元素转换成一个数组返回                  | 终值操作 |
| reduce   | 归约合并操作                                  | 中间操作 |
| collect  | 采集数据，返回一个新的结果                    | 终值操作 |
| distinct | 基于equal表达式去重                           | 中间操作 |



