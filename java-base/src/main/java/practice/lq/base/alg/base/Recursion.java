package practice.lq.base.alg.base;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 递归的使用
 */
public class Recursion {

    // 斐波那数
    int f1(int n){
        if(n<3){
            return 1;
        }else {
            return f1(n-1)+f1(n-2);
        }
    }

    // 爬楼梯
    // 只能爬1或2
    int f2(int n){
        if(n<3){
            return n;
        }else {
            return f2(n-1)+f2(n-2);
        }
    }

    // 汉罗塔




    public static void main(String[]args){
        Recursion recursion = new
                Recursion();
        System.out.println(recursion.f1(3)+";"+ recursion.f2(3));
    }

}
