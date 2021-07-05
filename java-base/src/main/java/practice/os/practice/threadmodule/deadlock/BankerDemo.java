package practice.os.practice.threadmodule.deadlock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/19
 * @VERSION 1.0
 * @DESC
 *
 */

public class BankerDemo {
    private final int maxPNum = 100;//最大进程数量
    private final int maxRNum = 100;//最大资源数量
    private int curPNum=0;
    private int curRNUm=0;
    //最大需求矩阵,0位需要空出来
    private int[][] maxMatrix={
            {0,7,5,3},
            {0,3,2,2},
            {0,9,0,2},
            {0,2,2,2},
            {0,4,3,3}
    };
            //new int[maxPNum][maxRNum];
    // 分配矩阵,第一位空出来判断该进程是否已经分配成功无需再进行分配
    private int[][] allocateMatrix = {
            {0,0,1,0},
            {0,2,0,0},
            {0,3,0,2},
            {0,2,1,1},
            {0,0,0,2}
    };// 若第一位等于0则未成功完成该进程分配，若为1则表示该进程已经成功分配）申请

    private int[] allResources = {10,5,7};
                    //new int[maxPNum][maxRNum];
    private int[] availableVector;


    //断点
    // 最大需求矩阵不会变，无需保存
//    private int[][] breakPointMaxMatrix={
//            {0,7,5,3},
//            {0,3,2,2},
//            {0,9,0,2},
//            {0,2,2,2},
//            {0,4,3,3}
//    };
    private int[][] breakPointAllocateMatrix;// 若第一位等于0则未成功完成该进程分配，若为1则表示该进程已经成功分配）申请
    private int[] breakPointAllResources;
    private int[] breakPointAvailableVector;

    void init(){
           curPNum = 5;//5个进程
           curRNUm = 3;//三个资源
           //allResources = new int[curRNUm];
           breakPointAllResources = new int[curRNUm];
           breakPointAvailableVector=new int[curRNUm];
           breakPointAllocateMatrix = new int[curPNum][curRNUm+1];
           availableVector=new int[curRNUm];
           System.out.println("init successfully...");       //...已初始化
    }
    // need矩阵无必要再设立？
    int[][] getNeedMatrix(){
        int [][]needMatrix = new int[maxPNum][maxRNum];
        for(int i = 0;i < curPNum;i++){
            for(int j = 0;j < curRNUm+1;j++){//空了一位，因此需要加1
                if(j==0){
                    if(allocateMatrix[i][j]==1){ //表示已经成功分配，无需再分配
                        needMatrix[i][j]=0;//表示该进程等待分配,若为1则说明已经成功分配，无需考虑再进行分配资源
                    }else{
                        // 表示该进程还未成功分配，还需要资源
                        needMatrix[i][j]=1;//表示该进程等待分配,若为1则说明已经成功分配，无需考虑再进行分配资源
                    }
                }
                else {
                    needMatrix[i][j]=maxMatrix[i][j]-allocateMatrix[i][j];
                }
            }
        }
        return needMatrix;
    }

    int[] getAvailableVector(){
        if(availableVector==null){
           System.err.println("System has‘nt allocated the resources,which is available.Please check " +
                   "the operator of init...");
        }
        int []availableV = new int[curRNUm];
        for(int j=1;j<curRNUm+1;j++){
            int sum=0;
            for(int i =0;i<curPNum;i++){
               if(allocateMatrix[i][j]==1){
                   sum+=0;//已成功分配，（该进程无需资源）
               }else{
                   sum+=allocateMatrix[i][j];
               }
            }

            availableV[j-1]=allResources[j-1]-sum;//
            System.out.println("has allocated resource"+(j-1)+":"+sum+";available :"+availableV[j-1]);
        }
        return availableV;
    }

    void workCheck(){
         int[][] tempNeed = getNeedMatrix();
         int[] tempAvailable=getAvailableVector();
         System.out.println("check variable...");
         System.out.println("maxNeed is...");
         for(int[] i:maxMatrix){
             for(int j:i){
                 System.out.print(j+"->");
             }
             System.out.println();
         }
         System.out.println("allocateMatrix is...");
         for(int[] i:allocateMatrix){
            for(int j:i){
                System.out.print(j+"->");
            }
            System.out.println();
         }
         System.out.println("allResources is...");
         for(Integer i:allResources){
             System.out.print(i+"->");
         }
         System.out.println();
         System.out.println("available is...");
         for(Integer i:tempAvailable){
             System.out.print(i+"->");
         }
         System.out.println();
         System.out.println("need is ...");
         for(int i=0;i<curPNum;i++){
             for(int j=0;j<curRNUm+1;j++){
                 System.out.print(tempNeed[i][j]+" ");
             }
             System.out.println();
         }
    }

    int counter =1;
    boolean isSafe(){
        Queue<String> queue = new ArrayDeque();//序列, 直接P+no即可，无需map映射

        //检查序列
        while (queue.size()!=curPNum){//当序列未满时则一直检查 &&isContinueAllocate
            boolean isContinueAllocate=false; //无需设置标志位，直接找出口即可？,每次扫描前都置为false，判断该次扫描有无获取可成功分配的
            System.out.println("第"+counter+"次扫描");
            System.out.println();

            // 检查need矩阵与可获取资源对比
            for(int i=0;i<curPNum;i++)
            {
                int[] tempAvailable = getAvailableVector();
                int[][] tempNeed = getNeedMatrix();//每进行进程分配之前都需要更新一下
                if(tempNeed[i][0]==0){//表示已经成功分配该进程，无再需资源
                    ///queue.add("P"+i); ,已添加过无需重复添加
                    if(!queue.contains("P"+i))
                        queue.add("P"+i);
                    if(queue.size()==curPNum){
                        while(!queue.isEmpty()){
                            System.out.print(queue.poll());
                        }
                        System.out.println();
                        return true;
                    }
                    continue;
                }


                boolean curFlag = true;// 判断当前进程是否可以成功分配
                for(int j=0;j<curRNUm;j++)
                {
                     if(tempNeed[i][j+1]>tempAvailable[j]){
                         curFlag=false;
                     }
                    //if(tempNeed[i][j+1])
                }

                if(curFlag){//可成功分配,进行序列添加,allocate调整 (有点贪心》)

                    // update allocateTable 和 available
                    allocateMatrix[i][0]=1;
                    for(int j=0;j<curRNUm;j++)
                    {
                        availableVector[j]= allocateMatrix[i][j+1]+availableVector[j];
                        allocateMatrix[i][j+1]=0;//置零
                    }
                     queue.add("P"+i);
                     if (i==curPNum-1&&queue.size()==curPNum){
                         System.out.println("safe...");
                         System.out.println("safe sequence is ...");
                         while(!queue.isEmpty()){
                             System.out.print(queue.poll());
                         }
                         System.out.println();
                         return true;
                     }

                     isContinueAllocate =true;//一旦出现成功分配的，则可以再进行一波扫描
                }else{// 不可成功分配，检查是否为最后一个且序列未满，若是则不安全，若不是则继续
                      // 不可分配，size必然不满
                     if(i==curRNUm-1&&queue.size()!=curRNUm)
                     {
                         counter++;
                         if(isContinueAllocate)
                             continue;
                         System.err.println("unsafe...");
                         return false;
                     }
                }
            }
        }
        return false;
    }

    boolean saveBreakPoint(){
        for(int i =0;i<curPNum;i++){
            for(int j =0;j<curRNUm+1;j++){
                breakPointAllocateMatrix[i][j]=allocateMatrix[i][j];
            }
        }

        for (int i =0;i<curRNUm;i++){
            breakPointAllResources[i]=allResources[i];
            breakPointAvailableVector[i]=availableVector[i];
        }

        return true;
    }

    boolean recoveryBreakPoint(){

        for(int i = 0;i < curPNum;i++){
            for(int j =0;j<curRNUm+1;j++){
                allocateMatrix[i][j]=breakPointAllocateMatrix[i][j];
            }
        }

        for (int i =0;i<curRNUm;i++){
            allResources[i] =  breakPointAllResources[i];
            availableVector[i]=breakPointAvailableVector[i];
        }

        return true;
    }

    // 判断是否能够安全分配
    boolean bankerHandle(int[] req,int pId){
          // 判断need
          //boolean flag = true;
          int[][] tempNeed = getNeedMatrix();
          int[] tempAvailable=getAvailableVector();

          for(int i=0;i<req.length;i++){
              if(req[i]>tempNeed[pId][i+1]){
                  System.err.println("request is unreasonable!!!");
                  return false;
              }
          }

          //保存当前状态
          saveBreakPoint();
          if(isSafe()){
              recoveryBreakPoint();
              return true;
          }else {
              recoveryBreakPoint();
              return false;
          }
    }


    void mockReq(){
          final int reqNum = 10;
          int[][] reqs= new int[reqNum][curRNUm];// 若无需该资源则为0

          for(int i =0;i<reqNum;i++){
            int pId = new Random().nextInt(5);

            System.out.println("Attention! process"+pId+" will request resource");
            for(int j = 0;j<curRNUm;j++){
                reqs[i][j]=new Random().nextInt(5);
                System.out.print(reqs[i][j]+">>");
            }
            bankerHandle(reqs[i],pId);


            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[]args){
         BankerDemo bankerDemo = new BankerDemo();
         bankerDemo.init();
         bankerDemo.workCheck();
         //bankerDemo.isSafe();
         bankerDemo.mockReq();
    }

}
