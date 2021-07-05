package practice.lq.base.alg.backtrack;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * n皇后
 */
public class EightQueen {

    static int n=3;
    // 棋盘
    static boolean [][] plain = new boolean[n][n];

    static {
        for(int i =0 ;i<n;i++){
            for(int j = 0 ;j<n;j++){
                plain[i][j]=false;
            }
        }
    }

    // 若只判断行，则是排列数排列
    boolean judge(int row,int col){//

        // 判断行?实际上不需要》

        // 判断列
        for(int i =0;i<row;i++){
            if(plain[i][col]==true){
                return false;
            }
        }

        // 判断斜方向



        return true;
    }

    void queen(int i){//,int j 下在第i+1行

        if(i>=n){
            for(int k=0;k<n;k++){
                for(int l=0;l<n;l++){
                    //System.out.print(plain[k][l]+" ");
                    if(plain[k][l]) //输出选择的数。
                        System.out.print(l);
                }
//                System.out.println();
            }
            System.out.println();
        }else{
            for(int k=0;k<n;k++){
                plain[i][k]= true;
                if(judge(i,k)){
                    queen(i+1);
                }
                plain[i][k]=false;//回退！！
            }
        }

    }

    public static void main(String[]args){
        EightQueen eightQueen = new EightQueen();
        eightQueen.queen(0);
    }

}
