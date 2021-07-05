package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/7
 * @VERSION 1.0
 * @DESC
 */
public class StringBase {
    public static void main(String[]args){
          String str = "";

          List<String> list = new ArrayList<>();
          list.add("hello");
          list.add("he");
          Collections.sort(list, (e1,e2)->e2.length()-e1.length());

          list.stream().forEach(System.out::println);
    }

}
