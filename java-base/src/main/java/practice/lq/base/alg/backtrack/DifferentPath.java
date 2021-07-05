package practice.lq.base.alg.backtrack;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/3
 * @VERSION 1.0
 * @DESC
 */
public class DifferentPath {
    int res  =0;
    void dfs(int row,int col,int m,int n){// 1,1开始
        if(row>m||col>n||row<1||col<1){
            return;
        }
        if(row==m&&col==n){
            res++;
            return;
        }

        dfs(row+1,col,m,n);
        dfs(row,col+1,m,n);
//        dfs(row-1,col,m,n);
//        dfs(row,col-1,m,n);
    }

    public int uniquePaths(int m, int n) {
        //    // 组合数 Cmn?  C max min-1 * A min-1
        //    int min = Math.min(m,n);
        //    int max = Math.max(m,n);
        //    int res = 1;
        //    for(int i=min-1;i>=1;i--){
        //        res*=i;
        //    }
        //    return max*res*(min-1);
        dfs(1,1,m,n);
        return res;
    }
    public static void main(String[]args){
        System.out.println(new DifferentPath().uniquePaths(3,7));
    }
}
