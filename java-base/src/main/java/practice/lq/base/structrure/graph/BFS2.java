package practice.lq.base.structrure.graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/9
 * @VERSION 1.0
 * @DESC
 * 1.问题
 * 注意X,Y和 row、col的对应关系
 * 2.
 */

// 点信息
class Point{
    int x,y;
    Point pro;// 前驱

    public Point(int x, int y, Point pro) {
        this.x = x;
        this.y = y;
        this.pro = pro;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getPro() {
        return pro;
    }

    public void setPro(Point pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +

                '}';
    }
}


public class BFS2 {
    //int n;
    static int[][] maze = new int[100][100];//new int[][]
    int tX[]={1,0,-1,0};
    int tY[]={0,-1,0,1};// 使用向量进行简化方向判断使用
    String[] steep={"R","U","L","D"};

    static {
        //FileInputStream fis = new FileInputStream("")
        try {
            InputStream ips = new FileInputStream("E:\\IdeaProjects\\java-base\\src\\think.lq\\base\\structrure\\graph\\maze.txt");
            InputStreamReader isr= new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(isr);
            String temp = null;

            int row=0,col=0;
            while((temp=br.readLine())!=null){
                col= temp.length();
                for(int i = 0;i<col;i++){
                 maze[row][i]=Integer.valueOf(temp.substring(i,i+1));
                }
                row++;
            }

            System.out.println("棋盘:");
            for (int i = 0;i<row;i++){
                for(int j = 0;j<col;j++){
                    System.out.print(maze[i][j]+" ");
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void bfs(){

        Queue<Point> queue = new ArrayDeque<>();// 也可以使用LinkedList

        queue.add(new Point(0,0,null));

        while (!queue.isEmpty()){

            Point cur = queue.poll();
            maze[cur.getY()][cur.getX()]=1;//访问

            if(cur.getX()==5&&cur.getY()==3){// 访问终点
                Stack<Point> stack = new Stack<>();

                while (cur.getPro()!=null){
                    stack.push(cur);
                    cur = cur.getPro();
                }

                System.out.println("可以访问到,且最近路径长度为:"+stack.size()+";轨迹为:");
                // 需要进行反向，也可以考虑递归回退打印
                String direction = "";
                stack.push(new Point(0,0,null));//加入起点

                while (!stack.isEmpty()){

                    Point cur2 = stack.peek();
                    stack.pop();
                    System.out.print(cur2+"->");

                    if(stack.isEmpty()){
                        break;
                    }else{
                        Point next = stack.peek();//不要删除,因下次是起点

                        for(int i = 0;i<4;i++){
                            if(cur2.getX()+tX[i]==next.getX()&&cur2.getY()+tY[i]== next.getY()){
                                direction=direction+steep[i];
                                break;
                            }
                        }
                    }
                }

                System.out.println();
                System.out.println("转向情况为:"+direction);
            }


            for(int i =0;i<4;i++){
               int x = cur.getX()+tX[i];
               int y = cur.getY()+tY[i];
               if(x>5||x<0||y<0||y>3)//排除边界
                   continue;

               if(maze[y][x]==0)// x 列 y 行 注意对应关系
               queue.add(new Point(x,y,cur));

            }

        }

    }
    public static void main(String[]args){
        BFS2 bfs2 = new BFS2();
        bfs2.bfs();
    }


}
