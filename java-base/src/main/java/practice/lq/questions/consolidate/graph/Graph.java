package practice.lq.questions.consolidate.graph;


import practice.lq.questions.consolidate.domain.Point;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/13
 * @VERSION 1.0
 * @DESC
 */
public class Graph {

    // 前置:BFS\DFS
    // 连通性和深搜 见lq\base\structrure\graph\BFS.java

    // 模拟数据
    static int[][]map = new int[100][100];//连通性
    static int[][]maze= new int[100][100];//迷宫
    // 读取测试文件
    static void printMap(int command){
//        if(command==1){
//           for(int i =0;i<)
//        }else {
//
//        }
    }
    static {

        try{
            String path = "E:\\IdeaProjects\\java-base\\src\\main\\java\\think.lq\\questions\\consolidate\\files\\maze";
            InputStream ips = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(ips);
            BufferedReader br =  new BufferedReader(isr);

            int row=0,col=0;
            String line = "";
            while((line=br.readLine())!=null){
                col=line.length();
                for(int i =0;i<line.length();i++){
                  maze[row][i]=Integer.valueOf(line.substring(i,i+1));
                }
                row++;
            }

            for(int i=0;i<row;i++){
                for(int j =0;j<col;j++){
                    System.out.print(maze[i][j]+" ");
                }
                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // BFS => 最短路径，如何走
    // 迷宫问题（可行》最短路径》路径》  BFS）
    // 定义Point
    // 设置方向量方便进行四个放行广度搜索
    int toRow[]={0,-1,0,1};
    int toCol[]={1,0,-1,0}; // 右下左上

    // 广搜
    // 重点理解，最先搜到的即为最短的，也就相当于树的深度。另外采用队列进行先加入的先进行搜索
    void bfs(){

        Queue<Point> queue = new ArrayDeque<Point>();

        // 启动
        //Point startP = new Point(0,0,null,1);// 若在类的属性中进行赋值对象则不能这样写
        Point startP = new Point(0,0,null,1);

        queue.add(startP);//添加启动
        while(!queue.isEmpty()){ //未找完则一直寻找，若找到目标点则进行退出

            Point cur = queue.poll();

            if(cur.getI()== 3&&cur.getJ()==5){
                // 找到目标点
                // cur就是一个链表头进行逆向打印即可，size()
                List<Point> list = new ArrayList<>();

                Point p = cur;
                while(p!=null)
                {
                    list.add(p);
                    p=p.getPre();
                }

                System.out.println("最短路径长度:"+list.size());
                System.out.println("最短路径:");

                for(int i =list.size()-1;i>=0;i--){
                    System.out.print("("+list.get(i).getI()+","+list.get(i).getJ()+")=>");
                }

                return;
            }

            // 遍历四个方向，寻找可进行加入的点
            for(int i = 0;i<4;i++){
                int nextI = cur.getI()+toRow[i];
                int nextJ = cur.getJ()+toCol[i];
                // 先进行边界判断
                if(nextI>=0&&nextJ>=0&&nextI<4&&nextJ<6&&maze[nextI][nextJ]==0){
                    Point next=new Point(nextI,nextJ,cur,0);//其实没有必要设立val,可以附加其他信息
                    queue.add(next);
                    // 需要标记当前点进行标记了吗
                    // 需要吧，因为加入队列已经标记搜索过该地方，其他能够搜索到该地方也只能慢于此，所以说没有必要在进行搜索了
                    // 那么思考，如何考虑所有的路径呢？？，就无需设立吗
                    maze[nextI][nextJ]=1;
                }
            }
        }
    }


    // 连通性判断
    // 思路进行遍历矩阵，发现1就进行深搜，并将其搜索的点进行visited二维数组标记
    static boolean[][] visited=new boolean[4][6];
    static {
        for(int i =0;i<4;i++){
            Arrays.fill(visited[i],false);
        }
    }


    // 无需前驱
    class Point2{
        int i,j;

        public Point2() {
        }

        public Point2(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }


    // 深搜起点
    void handleBfs(int startI,int startJ){

        Point2 startP = new Point2(startI,startJ);
        Queue<Point2> queue = new ArrayDeque<>();
        queue.add(startP);
        while (!queue.isEmpty()){

            Point2 cur = queue.poll();

            for(int i =0;i<4;i++){
                int nextI = cur.getI()+toRow[i];
                int nextJ = cur.getJ()+toCol[i];

                if(nextI<4&&nextJ<6&&nextI>=0&&nextJ>=0&&maze[nextI][nextJ]==1){
                    Point2 next = new Point2(nextI,nextJ);
                    queue.add(next);
                    System.out.println("进入在("+nextI+","+nextJ+")深搜");
                    maze[nextI][nextJ]=0;//若不设置此处未0，会进行一直搜索，死循环，或者判断时使用visited数组进行判断
                   // this.visited[nextI][nextJ]=true;//直接在maze上进行标记呢？？影响下次深搜》？
                    // 其实没有必要设立visited，直接使用maze即可，搜过的地方直接跳过
                }
            }
            if(queue.size()==0)
            {
                return;
            }
        }

    }

    // 判断联通的岛屿（处理大量时，此处简单小量数据只做展示）
    void connectivity(){
        int count=0;
        for(int i =0;i<4;i++){
            for(int j =0;j<6;j++){
                if(maze[i][j]==1){//未访问则进行深搜，且是在岛屿上  &&this.visited[i][j]==false
                    handleBfs(i,j);

                    count++;
                }
            }
        }
        System.out.println("岛屿数"+count);
    }


    // 七段码 a-g分别对应0-6
    //
    // 采用矩阵判断连通性比较方便
    int[][] map3={
            {0,1,0,0,0,0,1},
            {1,0,1,0,0,1,0},
            {0,1,0,1,0,1,0},
            {0,0,1,0,1,0,0},
            {0,0,0,1,0,1,1},
            {0,1,1,0,0,0,1},
            {1,0,0,0,1,1,0}
    };

    // 排列组合查看是否联通
    // 两两判断是否联通？
    void handle(){
        for(int i =1;i<=7;i++){//依次判断选取1-7个的组合
        }

        // 选1个为7
        // 选7个为1
        // 选6个为
    }


    // 法二，抽象成点
    /*
    0 0
    0 0
    0 0
    0 0
    a : (0,0)和(0,1)设置为1

    判断连通性：BFS
     */

    static int[][] map7={
            {0,0,0},
            {0,0,0},
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    static {

    }
    void writeMap(String str){
        System.out.println("写入棋盘"+str);
        for(int i =0;i<str.length();i++){
            String temp = str.substring(i,i+1);
            switch (temp){
                case "a":{map7[0][0]=map7[0][1]=map7[0][2]=1;break;}
                case "b":{map7[0][2]=map7[1][2]=map7[2][2]=1;break;}
                case "c":{map7[2][2]=map7[3][2]=map7[4][2]=1;break;}
                case "d":{map7[4][2]=map7[4][1]=map7[4][0]=1;break;}
                case "e":{map7[4][0]=map7[3][0]=map7[2][0]=1;break;}
                case "f":{map7[2][0]=map7[1][0]=map7[0][0]=1;break;}
                case "g":{map7[2][0]=map7[2][1]=map7[2][2]=1;break;}
            }
        }


        printMap7();


    }

    void bfs3(int startI,int startJ){

        Point2 startP = new Point2(startI,startJ);
        Queue<Point2> queue = new ArrayDeque<>();
        queue.add(startP);

        while (!queue.isEmpty())
        {
            Point2 cur = queue.poll();

            for(int i =0;i<4;i++){
                int nextI = cur.getI()+toRow[i];
                int nextJ = cur.getJ()+toCol[i];

                if(nextI>=0&&nextJ>=0&&nextI<5&&nextJ<3&&map7[nextI][nextJ]==1){
                    queue.add(new Point2(nextI,nextJ));
                    map7[nextI][nextJ]=0;//标记访问了
//                    printMap7();
                }
            }
        }
    }

    void toZero(){
        for(int i=0;i<5;i++){
            Arrays.fill(map7[i],0);
        }
    }

    boolean judge(String str){
        writeMap(str);// 填写棋盘
        int count=0;

        System.out.println("棋盘:");
        printMap7();

        for(int i =0;i<5;i++){
            for(int j=0;j<3;j++){
                if(map7[i][j]==1){
                    bfs3(i,j);
                    count++;
                }
            }
        }
        //count>1则非联通

        System.out.println(count);
        toZero();//归零

        if(count>1){
            return false;
        }else {
            return true;
        }

    }

    void printMap7(){
        for(int i =0;i<5;i++){
            for(int j = 0;j<3;j++){
                System.out.print(map7[i][j]+" ");
            }
            System.out.println();
        }
    }

    // 简化多重循环
    String simplyCirculation(String str,int n){// 循环的次数
    return "";
    }


    // 递归处理选取问题
    void recursion(String str,int start,int end,int val){
        if(end-start==val){
            System.out.println();
        }
        for(int i=start;i<end;i++){// 开始结束位置

        }
    }

    void handle2(){

        String str = "abcdefg";
        // 同样，1和7不用去考虑分配

        int sum =0;

        // 选取1个
        for(int i =0;i<str.length();i++){
            if(judge(str.substring(i,i+1))){
                sum++;
            }

        }

        // 如何排列组合 ，

        for(int i =1;i<=7;i++){ //选取问题，无需排列


        }

        // 选取2个数
        for(int i =0;i<6;i++){
            for(int j=i+1;j<7;j++){//多重循环
                String strs= str.substring(i,i+1)+str.substring(j,j+1);
                if(judge(strs)){
                    sum++;
                }
            }
        }

        // 选取3个数
        for(int i =0;i<5;i++){
            for(int j=i+1;j<6;j++){//多重循环
                for(int k=j+1;k<7;k++){
                    String strs= str.substring(i,i+1)+str.substring(j,j+1)+str.substring(k,k+1);
                    if(judge(strs)){
                        sum++;
                    }
                }
            }
        }


        // 选取4个数
        for(int i =0;i<4;i++){
            for(int j=i+1;j<5;j++){//多重循环
                for(int k=j+1;k<6;k++){
                   for(int l=k+1;l<7;l++){
                       String strs= str.substring(l,l+1)+str.substring(i,i+1)+str.substring(j,j+1)+str.substring(k,k+1);
                       if(judge(strs)){
                           sum++;
                       }
                   }
                }
            }
        }


        // 选取5个数
        for(int i =0;i<3;i++){
            for(int j=i+1;j<4;j++){//多重循环
                for(int k=j+1;k<5;k++){
                    for(int l=k+1;l<6;l++){
                        for(int n=l+1;n<7;n++){
                            String strs= str.substring(n,n+1)+str.substring(l,l+1)+str.substring(i,i+1)+str.substring(j,j+1)+str.substring(k,k+1);
                            if(judge(strs)){
                                sum++;
                            }
                        }
                    }
                }
            }
        }

        for(int i =0;i<2;i++){
            for(int j=i+1;j<3;j++){//多重循环
                for(int k=j+1;k<4;k++){
                    for(int l=k+1;l<5;l++){
                        for(int n=l+1;n<6;n++){
                           for(int c=n+1;c<7;c++){
                               String strs= str.substring(c,c+1)+str.substring(n,n+1)+str.substring(l,l+1)+str.substring(i,i+1)+str.substring(j,j+1)+str.substring(k,k+1);
                               if(judge(strs)){
                                   sum++;
                               }
                           }
                        }
                    }
                }
            }
        }

        System.out.println(sum);
    }


    // DFS
    // 规定一个方向进行搜索
//   （1）访问顶点v；
//   （2）依次从v的未被访问的邻接点出发，对图进行深度优先遍历；直至图中和v有路径相通的顶点都被访问；
//   （3）若此时图中尚有顶点未被访问，则从一个未被访问的顶点出发，重新进行深度优先遍历，直到图中所有顶点均被访问过为止。　
//     当然，当人们刚刚掌握深度优先搜索的时候常常用它来走迷宫.事实上我们还有别的方法，那就是广度优先搜索(BFS).

    // DFS 对比: DFS进行单条遍历直到走不通再回溯到另外一条，而BFS是将所有可能的都加入队列进行同时搜索
    // DFS 递归思想  BFS 队列
    // 采用邻阶表
    class DfsNode{
        int val;//该边的值，N叉树
        List<DfsNode> nextNodeList =new ArrayList<>();
        //

        public DfsNode() {
        }

        public DfsNode(int val, List<DfsNode> nextNodeList) {
            this.val = val;
            this.nextNodeList = nextNodeList;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public List<DfsNode> getNextNodeList() {
            return nextNodeList;
        }

        public void setNextNodeList(List<DfsNode> nextNodeList) {
            this.nextNodeList = nextNodeList;
        }
    }

    int step =1;
    static int[] visited1 =new int[100];// 如果值为对象可以使用map建立映射，或者hash函数
    static {
        Arrays.fill(visited1,0);
    }
    void dfs(DfsNode node){//目标边值
        System.out.println("第"+step+"步搜索");
        step++;
        visited1[node.getVal()]=1;//被访问
        if(node.getVal()==10){
            System.out.println("寻找到该边");
        }else if(node.getNextNodeList()==null){//需要前驱？？,八皇后，棋盘控制
            //回溯
             // 停止操作即可，即进入下一个
        }else {// 深搜
                for(int i =0;i<node.getNextNodeList().size();i++){
                    if(visited1[node.getNextNodeList().get(i).getVal()]==0){// 该边为被访问
                        dfs(node.getNextNodeList().get(i));
                    }
                }
             }
    }


    void test(){
        DfsNode node0= new DfsNode(0,null);
        DfsNode node1= new DfsNode(1,null);
        DfsNode node2= new DfsNode(2,null);
        DfsNode node3= new DfsNode(3,null);
        DfsNode node4= new DfsNode(4,null);
        DfsNode node10= new DfsNode(10,null);


        List<DfsNode> node0List = new ArrayList<>();
        node0List.add(node2);// 先搜索2
        node0List.add(node1);
        node0.setNextNodeList(node0List);
        List<DfsNode> node2List = new ArrayList<>();
        node2List.add(node3);
        node2List.add(node10);
        node2.setNextNodeList(node2List);
        dfs(node0);
    }
    public static void main(String[]args){
        Graph g= new Graph();
        g.test();
    }

}
