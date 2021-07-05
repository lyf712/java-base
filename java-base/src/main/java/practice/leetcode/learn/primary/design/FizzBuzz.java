package practice.leetcode.learn.primary.design;

import java.util.AbstractList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 */
public class FizzBuzz {

    // 法1 循环判断
    // 法2 继承实现抽象类

    List<String> fizzBuzz(int n){
        return new AbstractList<String>() {
            @Override
            public String get(int index) {
                index++;
                switch ((index%3==0?1:0)+(index%5==0?2:0)){
                    case 0:return String.valueOf(index);
                    case 1:return "Buzz";
                    case 2:return "Fizz";
                    case 3:return "FizzBuzz";
                    default:return "";
                }
//                return "";
            }
            @Override
            public int size() {
                return n;
            }
        };
    }
    public static void main(String[]args){
        new FizzBuzz().fizzBuzz(25).stream().forEach(System.out::println);
    }
}
