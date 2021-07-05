package practice.lq.base.alg.math;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 */
public class Gcd {

    // 转辗相除

    // 最大公约数
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


    public static void main(String[]args){

        Gcd gcd = new Gcd();
        System.out.println(gcd.gcd(10,21)+";"+gcd.lcm(10,21));


    }





}
