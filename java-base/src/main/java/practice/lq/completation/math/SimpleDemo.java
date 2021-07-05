package practice.lq.completation.math;

import java.util.List;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/30
 * @VERSION 1.0
 * @DESC
 */
public class SimpleDemo {

    /**
     * @param a
     * @param b
     * @return
     * 最大公约数
     * 1.根据其值判断是否互质
     * 2.
     */
    int gcd(int a,int b){
        if(a%b==0)
            return b;
        else
            return gcd(b,a%b);
    }
    // 最小公倍数
    int lcm(int a,int b){
        return a*b/gcd(a,b);
    }
    // 判断是否质数
    boolean isPrimer(int a){
        for(int i = 2;i*i<=a;i++){
            if(a%i==0)
                return false;
        }
        return true;
    }
    // 判断是否互质
    boolean isRelativePrimer(int a,int b){
        return gcd(a, b) == 1?true:false;
    }
    // 判断集合是否两两互质
    //Set<Integer> set = new HashSet<>();
    boolean isSetPrimer(List<Integer> list){
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                if(gcd(list.get(i),list.get(j))!=1){
                    return false;
                }
            }
        }
        return true;
    }

    // 约数定理


    public static void main(String[]args){
        SimpleDemo sd = new SimpleDemo();
        System.out.println("最大公约数:"+sd.gcd(6,12));
        System.out.println("最小公倍数:"+sd.lcm(6,12));
        System.out.println(sd.isPrimer(5)+";"+sd.isPrimer(4));
        System.out.println(sd.isRelativePrimer(4,5)+";"+sd.isRelativePrimer(4,6));
    }


}
