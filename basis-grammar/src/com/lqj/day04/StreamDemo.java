package com.lqj.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    }
}
