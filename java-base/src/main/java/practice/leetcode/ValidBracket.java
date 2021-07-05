package practice.leetcode;

import java.util.Stack;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/2
 * @VERSION 1.0
 * @DESC
 */
public class ValidBracket {

    // 1.设立一个栈进行存储，若遇到匹配的则进行出栈，直到最后 <= 匹配需要进行映射
    // 2.栈不剩元素则true

    boolean compare(char a,char b){
        if(a==')'||a==']'||a=='}')
            return false;
        else {
            switch (a){
                case '(':{if(b==')') return true; else return false;
                }
                case '[':{if(b==']') return true; else return false;
                }
                case '{':{if(b=='}') return true; else return false;
                }
            }
        }
        return false;
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for(int i=1;i<s.length();i++){
            if(compare(stack.peek(),s.charAt(i))){
                stack.pop();
            }else {
                stack.push(s.charAt(i));
            }
        }

        if(stack.size()==0)
            return true;

        return false;
    }

}
