package leetcode.stack;

import java.util.Stack;
import java.util.function.Predicate;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/6
 * @VERSION 1.0
 * @DESC
 * Stack的基础使用
 * 继承Vector (继承AbstractList ,实现List<>)
 * 拓展属性
 * pop,search,push,empty。。
 * 集合继承过来，保留集合的特性。。
 *
 */
public class StackBase {
    public static void main(String[]args){

        Stack<String> stack = new Stack<>();
        stack.push("e2");
        stack.push("e3");
        stack.add("e1");

        // stream遍历当做数组未满足先进后出原则
        stack.stream().forEach(System.out::println);
        while (!stack.isEmpty()){
            System.out.print(stack.peek());
            stack.pop();
        }

        System.out.println();
        System.out.println("------");
        stack.add("e1");
        stack.add("e2");

        // 指定位置获取,破坏栈的特性
        stack.get(1);
        System.out.println(stack.search("e1"));
        stack.lastIndexOf("e1");

        // 指定条件移除
        stack.removeIf(Predicate.isEqual("e2"));
        stack.stream().forEach(System.out::println);
        stack.remove(2);//指定位置移除
        stack.remove("e1");


    }
}
