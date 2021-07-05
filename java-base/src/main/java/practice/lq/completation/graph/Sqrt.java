package practice.lq.completation.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 * 平方拆分
 */
public class Sqrt {
    int ans = 0;
    List<Integer> list = new ArrayList<>();

    void dfs(int m,int n){// m为分解出来的数,n为待分解的数
        if(n==0){
            ans++;
            list.stream().forEach(e->{
                System.out.print(e+"+");
             });
            System.out.println();
            list.clear();
            return;
        }

        // 分解m+1到
        for(int i=m+1;i*i<=n;i++){
            list.add(i);
            dfs(i,n-i*i);
            list.remove((Object)i);
        }
    }
    void rs(){
        dfs(0,2019);
        System.out.println("RS:"+ans);
    }
    public static void main(String[]args){
        new Sqrt().rs();
    }

}
