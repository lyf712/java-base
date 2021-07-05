package practice.lq.base.alg.backtrack;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class NQueen {

    final int N = 4;// 棋盘边长
    boolean [][] box = new boolean[N][N]; // 棋盘,共同维护

    boolean isValid(int x,int y){

        // 检测横竖
//        for(int i=0;i<N;i++){
//            int flag1 = 0,flag2=0;
//            for (int j=0;j<N;j++){
//                if(box[j][i]){//竖
//                    flag1++;
//                }
//                if(box[i][j]){//横
//                    flag2++;
//                }
//            }
//            if(flag1>1||flag2>1){
//                return false;
//            }
//        }

        return true;
    }

    void dfs(int x,int y){

//        if(!isValid(x,y)){// 无序终止
//            return;
//        }

//        if(x<0||y<0||x>=N||y>=N){
//            return;
//        }

        if(y==N){
            //停止,打印棋盘
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(box[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("--------");
            return;
        }else {
            for(int i=0;i<N;i++){
                if(!box[y][x]&&isValid(x,y)){
                    box[y][x]=true;//选择该位置
                    dfs(i,y+1);// 从0开始摆放
                    box[y][x]=false;// 回退
                }
            }
        }

        //------


    }

    void handle(){

    }
    public static void main(String[]args){
        new NQueen().dfs(0,0);
    }

}
