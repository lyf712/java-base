package practice.lq.questions.country_2019;

import java.util.HashSet;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/31
 * @VERSION 1.0
 * @DESC
 * 序列求和
 */
public class T5 {
    public int reverse(int x) {
        if(x==0)
            return 0;

        String str = x+"";
        int i = x<0?1:0;
        int j = str.length()-1;
        String str1="",str2="";
        while(i<j){

            str1=str1+str.substring(j,j+1);
            str2=str2+str.substring(i,i+1);
            i++;
            j--;
        }
        str = str1+str2;

        long val = Long.valueOf(str);
        if(val>Integer.MAX_VALUE){
            return 0;
        }else {
            return Integer.valueOf(str);
        }
    }


    int count(int n){

        if(n==1||n==2){
            return n;
        }
        Set<Integer> set = new HashSet<>();
        set.add(1);
        for (int i=2;i<=n;i++){
            if(n%i==0){
                set.add(n/i);
                n/=i;
            }
        }

       return set.size();
    }
    // 质数 1,2,3,5,7,11
    int[]hash = new int[61];
    int sum(){

        int flag = 0;
        for(int i=1;;i++){
            int temp = count(i);
            if(hash[temp]==0){
                System.out.println(i);
                hash[temp]=i;
                flag++;
                if(flag==60)
                    break;
            }
        }

        int sum=0;
        for(int i=1;i<=60;i++)
            sum+=hash[i];
        return sum;
    }
    public static void main(String[]args){
        //System.out.println(new T5().sum());
        for(int i=1;i<1000;i++)
          System.out.println(i+":"+Math.exp(1.043*i));
    }

}
