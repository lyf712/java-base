package practice.lq.base.alg.math;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 */
public class Primer {

    boolean getPrimer(int n){

        for(int i =2;i*i<=n;i++){
            if(n%i==0)
                return false;
        }

        return true;
    }

}
