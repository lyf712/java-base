package practice.lq.completation.recursion;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/31
 * @VERSION 1.0
 * @DESC
 * 1.4的幂、2的幂
 */
public class T2 {
    // 2 的幂的位运算方法
    boolean isPowerOfTwo(int n){
        return n>0&&(n&(n-1))==0;
    }

    boolean isPowerOfFour(int n){
        if(n<=0){
            return false;
        }else{
            if(n%4==0){
                return isPowerOfFour(n/4);// 若是4的倍数则,进行递归 否则判断
            }else if(n==1){// 本来就是1或除4到等于1
                return true;
            }else// 最终非1,说明非4的幂数
            {
                return false;
            }

        }
    }
    // 时间100%,内存27
    int [] hash = new int[101];
    public int numWays(int n) {
        if(n==0){
            hash[n]=1;
            return 1;
        }
        if(n==1||n==2){
            hash[n]=n;
            return n;
        }else {
            if(hash[n-2]!=0){
                return (numWays(n-1)+hash[n-2])%1000000007;
            }else{
                hash[n-2]=numWays(n-2);
                return (numWays(n-1)+numWays(n-2))%1000000007;
            }
        }
    }

    /**
     *
     * class Solution {
     *     int [] hash = new int[101];
     *     public int numWays(int n) {
     *         if(n==0){
     *             hash[n]=1;
     *             return 1;
     *         }
     *         if(n==1||n==2){
     *             hash[n]=n;
     *             return n;
     *         }else {
     *             if(hash[n-2]!=0){
     *                 return (numWays(n-1)+hash[n-2])%1000000007;
     *             }else{
     *                 hash[n-2]=numWays(n-2);
     *
     *                 if(hash[n-1]!=0){
     *                       return (hash[n-1]+hash[n-2])%1000000007;
     *                 }else{
     *                       hash[n-1]=numWays(n-1);
     *                       return (numWays(n-1)+numWays(n-2))%1000000007;
     *                 }
     *
     *             }
     *         }
     *     }
     * }
     * @param args
     */

    public static void main(String[]args){
        T2 t2 = new T2();
        System.out.println(t2.numWays(43));
    }

}
