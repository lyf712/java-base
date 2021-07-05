package practice.lq.completation.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/30
 * @VERSION 1.0
 * @DESC
 */
public class DFS {

    class Point{
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    List<List<Point>> list = new ArrayList<>();
    List<Point> record = new ArrayList<>();//记录一次轨迹
    /**
     *
     * @param matrix
     * @param count
     * @param x
     * @param y
     * @return
     *  * 玩具蛇（国赛第五题）
     *    * 计算数量即可,使用一个棋盘记录是否选择该位子（->排列组合？是否选择该字符
     *    * -> 回溯法,当选择不合理时进行回退
     *    * 1.
     *
     */
    int dfs(boolean [][]matrix,int count,int x,int y){

        if(x>=4||y>=4||x<0||y<0)
            return 0;// 选择不合理,计数为0,思考记录路径
        if(count==0)
            record.clear();
        if(matrix[x][y])
        {
            record.add(new Point(x,y));
            return 0;//已走
        }

        if(count==15)
        {
            list.add(record);
            return 1;// 成功选择完,具有一种可能性
        }

        int sum=0;
        // 表示已经走过该位子
        matrix[x][y] = true;
        sum+=dfs(matrix,count+1,x-1,y);//往左走
        sum+=dfs(matrix,count+1,x+1,y);//往右走
        sum+=dfs(matrix,count+1,x,y-1);
        sum+=dfs(matrix,count+1,x,y+1);
        matrix[x][y]=false;//若四个方向都走完则进行回退
        return sum;
    }
    void test(){
        boolean [][]matrix=new boolean[4][4];
        int sum=0;
        for(int i=0;i<16;i++){// 从16个位子进行分别开始深搜
            sum+=dfs(matrix,0,i/4,i%4);
        }
        System.out.println("RS:"+sum);
        list.stream().forEach(e->{
            e.stream().forEach(System.out::print);
            System.out.println();
        });
    }


    /**
     * 排列组合字符串
     */
    String str = "abc";
    String temp = "";
    List<String> recordStr = new ArrayList<>();
    int permutation(boolean[] select,int hasSelect,String str,int pos){
        if(pos<0||pos>=3){
            return 0;
        }
         if(select[pos]){
            System.out.println(pos);
            temp=temp+str.substring(pos,pos+1);
            return 0;//该位子已选
        }
         if(hasSelect==3){// 已选三个
            recordStr.add(temp);
            return 1;
        }

        int sum = 0;
        select[pos]=true;
        sum+=permutation(select,hasSelect+1,str,pos+1);
        sum+=permutation(select,hasSelect+1,str,pos-1);
        select[pos]=false;
        return sum;
    }
    void test2(){
        boolean [] s = new boolean[3];
        int sum =0;
        for(int i=0;i<3;i++){
            sum+=permutation(s,0,"abc",i);
        }
        System.out.println("RS:"+sum);
//        recordStr.stream().forEach(
//                e->{
//                    System.out.print(e+"->");
//                }
//        );
    }


    /**
     * 七段码
     * @param args
     */


    public static void main(String[]args){
        DFS d = new DFS();
        d.test2();
    }


}
