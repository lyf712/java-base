package practice.lq.completation.recursion;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class T5 {
    public int[] divingBoard(int shorter, int longer, int k) {
        // 迭代,shorter从选0次到k次

        if(k==0){
            int []nums = new int[0];
            return nums;
        }

        Set<Integer> set = new HashSet<>();

        for(int i=0;i<=k;i++){
            set.add(i*longer+(k-i)*shorter);
            System.out.println(i*longer+(k-i)*shorter);
        }


        System.out.println("----------------------");
        int []nums = new int[set.size()];
        System.out.println(set.size());
        //nums = (int[]) set.toArray();

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        AtomicInteger i = new AtomicInteger();
        set.stream().forEach(e->{
            nums[i.get()]=e.intValue();
            System.out.println(e);
            i.getAndIncrement();
        });

        Arrays.sort(nums);
//        Integer [] hello = new Integer[];
      //  hello  =(Integer[]) new ArrayList().toArray();

        return nums;
    }
    public static void main(String[]args){
      //  new T5().divingBoard(2,1118596,979);
        System.out.println("0".replace("0","01"));

        System.out.println(4^2);
        System.out.println(Byte.valueOf(4+"").byteValue());
        System.out.println(~4);
        System.out.println(Integer.toBinaryString(3));

        Stack<Integer> stack = new Stack<>();
        //stack.push()
        stack.peek();
       // new ArrayList<>().re
    }
}
