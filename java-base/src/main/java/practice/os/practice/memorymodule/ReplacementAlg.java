package practice.os.practice.memorymodule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/23
 * @VERSION 1.0
 * @DESC
 * 优质参考（C语言版）：https://www.cnblogs.com/wasi-991017/p/13072328.html
 * 缺页和置换的概念
 * 当页面内存中不存在时，则出现缺页需要调入。
 * =>在调入时，内存已满，需要选择某个页面进行调出，则需要置换
 * 1.FIFO
 * 2.LRU
 * 3.OPT
 * OPT算法是无法实现的，因为，在程序运行过程中无法对以后要使用的页面做出精确的断言
 *
 * 4.CLOCK
 */
public class ReplacementAlg {
    // 页面请求序列
    static private List<Integer> reqPageSeq = new ArrayList<>();
    // 内存容量
    /**
     * @PARAM
     *
     */
    static int size = 6;

    static {
        for(int i=0;i<256;i++){
            reqPageSeq.add(new Random().nextInt(10));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PageStreamSeq...");
        reqPageSeq.stream().forEach(e->System.out.print(" "+e));

    }

    /**
     * FIFO
     */
    void fifoStrategy(){
         System.out.println("FIFO...");
         AtomicInteger allPages = new AtomicInteger();
         AtomicInteger missPages = new AtomicInteger();
         AtomicInteger replacePages = new AtomicInteger();
         allPages.set(0);
         missPages.set(0);
         replacePages.set(0);
         Queue<Integer> blocks = new ArrayDeque<>();
//         blocks.add(4);
//         blocks.add(1);
         reqPageSeq.stream().forEach(
                 e->{
                     allPages.getAndIncrement();
                     if(!blocks.contains(e)){
                         // 不存在则缺页
                         missPages.getAndIncrement();
                         if(blocks.size()==size){
                             // 置换
                             replacePages.getAndIncrement();
                             // FIFO 先进来的先置换出去
                             blocks.poll();
                         }
                         blocks.add(e);
                     }
                     System.out.println("memory:"+(size-blocks.size())+";allPages:"+allPages.get()+";missPages:"+missPages.get()+";replacePages:"+replacePages.get()+
                             ";missPageRate:"+Float.parseFloat(missPages+"")/Float.parseFloat(allPages+""));
                 }
         );
    }

    /**
     * LRU算法
     * 采用栈记录物理块，当出现命中的块时，将其调整到栈顶
     * 未出现则判断是否满内存，若满则删除栈底元素，添加新的元素到栈顶
     */
    void lruStrategy(){
        System.out.println("LRU...");
        AtomicInteger allPages = new AtomicInteger();
        AtomicInteger missPages = new AtomicInteger();
        AtomicInteger replacePages = new AtomicInteger();
        allPages.set(0);
        missPages.set(0);
        replacePages.set(0);
        Stack<Integer> stack = new Stack<>();
        reqPageSeq.stream().forEach(e->{
            allPages.getAndIncrement();
            System.out.println("stack0..");
            stack.stream().forEach(e1-> System.out.print(e1+" "));
            if(!stack.contains(e)){
                missPages.getAndIncrement();
                if(stack.size()==size){
                    replacePages.getAndIncrement();
                    // 移除栈底元素
                    stack.remove(0);
                }
            }else{// 包含，则考虑调整优先级，将其放在栈顶
                stack.remove((Object)e);
            }
            stack.add(e);
            System.out.println("stack1..");
            stack.stream().forEach(e1-> System.out.print(e1+" "));
            System.out.println();
            System.out.println("memory:"+(size-stack.size())+";allPages:"+allPages.get()+";missPages:"+missPages.get()+";replacePages:"+replacePages.get()+
                    ";missPageRate:"+Float.parseFloat(missPages+"")/Float.parseFloat(allPages+""));
        });

    }

    void optStrategy(){
        System.out.println("OPT...");
        int allPages = 0;
        int missPages = 0;
        int replacePages = 0;
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<reqPageSeq.size();i++){
            allPages++;
            if(!list.contains(reqPageSeq.get(i))){
                missPages++;
                if(list.size()==size){//移除
                    replacePages++;
                    List<Integer> tempList = new LinkedList<>();
                    tempList.addAll(list);
                    for(int j=i+1;j<reqPageSeq.size();j++){
                        if(tempList.size()==1){
                            // 若移除的只剩下一个，那么只能是这个了，否则一直移动
                            break;
                        }else{
                            tempList.removeIf(Predicate.isEqual(reqPageSeq.get(j)));
                        }
                    }
                    list.remove(tempList.get(0));
                }
                list.add(reqPageSeq.get(i));
            }
        }
        System.out.println();
        System.out.println("memory:"+(size-list.size())+";allPages:"+allPages+";missPages:"+missPages+";replacePages:"+replacePages+
                ";missPageRate:"+Float.parseFloat(missPages+"")/Float.parseFloat(allPages+""));
    }

    public static void main(String[]args){
        ReplacementAlg replacementAlg = new ReplacementAlg();
        replacementAlg.fifoStrategy();
        replacementAlg.lruStrategy();
        replacementAlg.optStrategy();
    }




}
