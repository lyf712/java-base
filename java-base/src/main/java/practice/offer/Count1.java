package practice.offer;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 * 题目描述：输入一个整数，输出该数二进制表示中 1 的个数。其中负数用补码表
 * 示。
 * 思路 思路：a&(a-1)的结果会将 a 最右边的 1 变为 0，直到 a = 0，还可以先将 a&1 !=
 * 0，然后右移 1 位，但不能计算负数的值，
 *
 */
public class Count1 {
    int countOne(int num){
        int counter=0;
        while(num>0){
            if(num%2==1)
                counter++;
            num/=2;
        }
        return counter;
    }
    public static void main(String[]args){
        Count1 count1 = new Count1();
        //int val = count1.countOne(2);
        for(int i=0;i<10;i++)
        System.out.println(count1.countOne(i));
    }
}
