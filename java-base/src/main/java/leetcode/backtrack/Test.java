package leetcode.backtrack;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/14
 * @VERSION 1.0
 * @DESC
 */
public class Test {
    void dfs(String val,int n,int r){
        if(val.length()==r){
            System.out.println(val);
            return;
        }
        if(n<1){
            return;
        }
        dfs(val+n,n-1,r);
        dfs(val,n-1,r);
    }
    public static void main(String[]args){
        new Test().dfs("",6,3);
    }
}
