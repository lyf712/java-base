package practice.lq.questions.country_2020;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/26
 * @VERSION 1.0
 * @DESC
 */
public class T5_DFS {
    long dfs(boolean [][]matrix,int inX,int x,int y){
        if(x<0||y<0||x>=4||y>=4)
          return 0;
        if(matrix[y][x]) // 该位置已走
          return 0;
        if(inX==15)
          return 1;

        int rs =0;
        matrix[y][x] =true;
        rs += dfs(matrix,inX+1,x-1,y);//往左走
        rs += dfs(matrix,inX+1,x,y-1);
        rs += dfs(matrix,inX+1,x+1,y);
        rs += dfs(matrix,inX+1,x,y+1);
        matrix[y][x]=false;//回退
        return rs;
    }

    public static void main(String[]args){
        T5_DFS t5_dfs= new T5_DFS();
        boolean[][] matrix=new boolean[4][4];
        long counter =0;
        for (int i=0;i<16;i++){
            counter+=t5_dfs.dfs(matrix,0,i%4,i/4);
        }
        System.out.println(counter);
    }
}
