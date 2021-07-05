package practice.lq.base.structrure.graph;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 思路类似层次遍历
 * 将所有符合要求的领边加入队列，进行搜索，直到遇到符合要求的值或者队列为空
 *
 * BFS:https://blog.csdn.net/weixin_44593822/article/details/104204397
 *
 * BFS打印最短路径：http://www.myexceptions.net/arithmetic/1964524.html
 *
 */
public class BFS {

    // 数组方法实现图
    static String[][] matrix  = new String[10][10];
    static String[][] matrix2 ={
            {"*","*","*"},
            {"*","#","*"},
            {"*","#","*"}
    };

    static int[][] maze=new int[100][100];
    class Point{
        int level ;//第几层的节点
        Point pro=null;// 前驱节点
        int i,j;


        public Point(){

        }

        public Point(Point p,int level,int i,int j ){
            this.pro = p;
            this.level = level;
            this.i = i;
            this.j = j;
        }

        public void setPro(Point pro) {
            this.pro = pro;
        }

        public Point getPro() {
            return pro;
        }

        public int getLevel() {
            return level;
        }
        public void setLevel(int level) {
            this.level = level;
        }
        public int getI() {
            return i;
        }
        public int getJ() {
            return j;
        }
        public void setJ(int j) {
            this.j = j;
        }
        public void setI(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    static {

        try {
            InputStream ips = new FileInputStream("E:\\IdeaProjects\\java-base\\src\\think.lq\\base\\structrure\\graph\\maze.txt");
            InputStreamReader isr  = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(isr);
            String temp =null;
            int col=0,row=0;

            while((temp=br.readLine())!=null){
                col=temp.length();
                for(int i =0;i<col;i++){
                    maze[row][i]=Integer.valueOf(temp.substring(i,i+1));
                }
                row++;
            }


            System.out.println("输出结果");
            for(int i = 0;i<row;i++){
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

    // 迷宫问题


    // 广度搜索：将符合要求的加入队列
    // 思想，将周围或者领边符合要求的加入队列，进行处理，直到遇到出口，遇不到则进行按此规则进行处理
    // 1.判断是否能够走通
    // 2.判断最短路径长度？？记录层次即可
    // 3.如何记录路径呢？？？,使用前驱即可，由终点倒退在逆置
    // 其实就是一个四岔树？？
    // => 优化点: 进行方向向量简化，也容易避免出错


    void printRs(Point p){

//        if(p.pro!=null){
//            printRs(p.pro);
//        }else {
//            System.out.println(p);
//        }
//        if(p.getPro()==null)
//            System.out.println(p);
//        printRs(p.getPro());

    }

    void bfs(){
     // maze[3][5]=2;// 为方便处理，改变其数字，设置为出口
      // 深搜目标，即找到2，入口则是0，0

        // 存储的是方向还是目标？？存储的坐标，定义一个class,C++上为结构体
        Queue<Point> q = new ArrayDeque();
//        Stack<Point> q = new Stack<>();
        int level = 0;//通过节点记录level??

        List<Point> line = new ArrayList<>();// 轨迹
        List<List<Point>> lineList =new ArrayList<>();// 所有轨迹

        q.add(new Point(null,0,0,0));// 加入起始点
        maze[0][0]=1;// 表示不能回退已经访问此节点

        while(q.size()>0){ // 当队列非空时则进行继续寻找

            // 将周边符合要求的加入队列(即0,1为障碍)

            Point p = q.poll(); //当前点，获取之后需要进行删除
//            Point p=q.peek();
//            q.pop();
            // 走过的地方也得标记为1
           // line.add(p);//
           line.add(p);
            System.out.println("访问当前点:"+p);

            if(p.getI()==3&&p.getJ()==5){ // maze[p.getI()][p.getJ()]==2,若定义该点为非0点，在加入队列时需要进行考虑调整
                System.out.println("可到达，路径最短为"+p.getLevel()+";"+p+";行走轨迹为:");
                 Point temp = p;
                // 递归打印
                printRs(p);
                System.out.println(p.getPro());

                Stack<Point> stack = new Stack<>();//有了此记录，就无需level了，size则为最小值

                while(p!=null){
                    stack.push(p);
                    p =p.getPro();
                }

                String direction = "";
                AtomicReference dir =new AtomicReference();

                // 处理转向方向问题
                stack.stream().forEach((e)->{
                    System.out.print(e+"->");
                });
                System.out.println();
                while(!stack.isEmpty()){
                    // 栈顶即为起点
                    Point cur = stack.peek();
                    stack.pop();

                    if(stack.isEmpty()){ // 下一点已经为空则为终点了
                        break;
                    }else{
                        Point next = stack.peek();
//                        stack.pop();

                        // 判断如何转向
                        if(cur.getI()==next.getI()&&cur.getJ()-next.getJ()==-1){ //向右
                            direction=direction+"R";
                        }else if(cur.getI()==next.getI()&&cur.getJ()-next.getJ()==1){
                            direction=direction+"L";
                        }else if(cur.getI()-next.getI()==1&&cur.getJ()-next.getJ()==0){
                            direction=direction+"U";
                        }else if(cur.getI()-next.getI()==-1&&cur.getJ()-next.getJ()==0){
                            direction=direction+"D";
                        }
                    }
                }
                System.out.println("转向情况:"+direction);



                System.out.println();

                line.forEach((e)->{
                    System.out.print(e+"->");
                });
                return;
            }
            maze[p.getI()][p.getJ()]=1;

            // 遍历四个方向
            if(p.getJ()+1<=5&&maze[p.getI()][p.getJ()+1]==0){// 向右走可行
                q.add(new Point(p,p.getLevel()+1,p.getI(), p.getJ()+1));

//                maze[p.getI()][p.getJ()+1]=1;
            }

            if(p.getI()+1<=3&&maze[p.getI()+1][p.getJ()]==0){// 向下走可行
                q.add(new Point(p,p.getLevel()+1,p.getI()+1, p.getJ()));
//                maze[p.getI()+1][p.getJ()]=1;
            }

            if(p.getJ()-1>=0&&maze[p.getI()][p.getJ()-1]==0){// 向左走可行
                q.add(new Point(p,p.getLevel()+1,p.getI(), p.getJ()-1));
//                maze[p.getI()][p.getJ()-1]=1;
            }

            if(p.getI()-1>=0&&maze[p.getI()-1][p.getJ()]==0){// 向上走可行
                q.add(new Point(p,p.getLevel()+1,p.getI()-1, p.getJ()));
//                maze[p.getI()-1][p.getJ()+1]=1;
            }
            q.stream().forEach(System.out::print);
            System.out.println();
        }
        System.out.println("不可达到");
    }


    void dfs(){




    }

    public static void main(String[]args){
        BFS bfs = new BFS();
        bfs.bfs();
    }

}
