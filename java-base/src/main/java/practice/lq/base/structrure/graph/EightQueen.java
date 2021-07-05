package practice.lq.base.structrure.graph;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 *
 */

public class EightQueen {

    static private int [][]matrix=new int[10][10];
    static {
//        Arrays.fill(matrix,0);

        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                matrix[i][j]=0;
            }
        }
    }



    boolean j(int i,int j){// 判断该子儿落下是否可行

        //检查列
        for(int row=0;row<=i;row++){
            if(matrix[row][j]==1){
                return false;
            }
        }

        //检查斜方向


        return true;
    }


    void eQ(int n){ //n代表下到第几行了

        if(n==10){
            for(int k =0;k<10;k++){
                        for(int j =0;j<10;j++){
                            System.out.print(matrix[k][j]+" ");
                        }
                        System.out.println();
                    }
        }

        for(int i =0;i<10;i++){

            if(j(n,i)){
                matrix[n][i]=1;//落子
                eQ(n+1);
                matrix[n][i]=0;//方便回退
            }



//            if(j(n,i)){
//                if(n==9){
//                    //输出
//                    for(int k =0;k<10;k++){
//                        for(int j =0;j<10;j++){
//                            System.out.print(matrix[k][j]+" ");
//                        }
//                        System.out.println();
//                    }
//                }else{
//                    eQ(n+1);
//                }
//            }



        }
    }






    public static void main(String[]args){
        EightQueen eq = new EightQueen();
        eq.eQ(0);
    }

}
