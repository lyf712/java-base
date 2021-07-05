package practice.offer;

import java.util.Stack;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 */
public class TowStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> bufferStack = new Stack<>();

    // 存入使用stack存入,删除使用bufferStack逆转删除
    void push(int val){
        stack.push(val);
    }
    void pop() throws Exception{
        if(stack.isEmpty()&&bufferStack.isEmpty()){
            throw new Exception("null");
        }
        if(bufferStack.isEmpty()){
//            stack.stream().forEach(e->{
//                bufferStack.push(e);
//            });
            while(!stack.isEmpty()){
                bufferStack.push(stack.peek());
                stack.pop();
            }
        }
        bufferStack.pop();//删除栈底元素则队列队首元素
        //复原
        while(!bufferStack.isEmpty()){
            stack.push(bufferStack.peek());
            bufferStack.pop();
        }
    }

    void test(){
        stack.stream().forEach(System.out::println);
    }

    public static void main(String[]args) throws Exception {
        TowStack towStack = new TowStack();
        towStack.push(2);
        towStack.push(3);
        towStack.push(4);
        towStack.test();
        towStack.pop();
        towStack.test();
    }


}
