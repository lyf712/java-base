package practice.lq.base.alg.backtrack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class NQueen2 {
    final int N = 4;
    boolean isValid(boolean [][]matrix,int row,int col){
        for(int i=0;i<N;i++){
            int flag0=0,flag1=0;
            for(int j=0;j<N;j++){
                if(matrix[i][j])
                    flag0++;
                if(matrix[j][i])
                    flag1++;
            }
            if(flag0>1||flag1>1){
                return false;
            }
        }
        return true;
    }

    void dfs(boolean[][]matrix,int row,int col){
        AtomicInteger integer = new AtomicInteger();
       // integer.getAndIncrement()
        if(!isValid(matrix,row,col)){
            return;
        }

        if(row == matrix.length)
        {
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("--------------------");
            return;
        }

        for(int i=0;i<N;i++){
            //if(!matrix[row][col]&&isValid(matrix,row,col)){// 该位置未下且有效
                matrix[row][i] = true; //matrix[row][col] = true;
                if(isValid(matrix,row,i))
                    dfs(matrix,row+1,i);
                matrix[row][i] = false;
            //}
        }
    }

   // void handle(int )

    public static void main(String[]args){
        NQueen2 t = new NQueen2();
        boolean[][] matrix = new boolean[t.N][t.N];
        t.dfs(matrix,0,0);
    }

}
