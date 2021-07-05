package practice.lq.questions.country_2019;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/23
 * @VERSION 1.0
 * @DESC
 * 拆平方和
 */
public class T2 {
    public static void squareSplit(int num) {
        System.out.println(process(num, 0));
    }

    public static int process(int num,int cur) {
        if(num<0) return 0;
        if(num==0) return 1;
        int result=0;
        for(int i=cur+1;i<=Math.sqrt(num);i++) {
            result+=process(num-i*i,i);// 累加当前为拆出来 i^2的数量 ,拆到直至 num为0或1
        }

        return result;
    }

    public static void main(String[] args) {
        int num=2019;
        squareSplit(num);
    }
}
