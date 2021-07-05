package leetcode.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/6
 * @VERSION 1.0
 * @DESC
 */
public class HashSetDemo {
    public static void main(String[]args){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(0);
        System.out.println(set.stream().max(Comparator.naturalOrder()).get());


    }
}
