package practice.lq.base.alg.dp;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/4
 * @VERSION 1.0
 * @DESC
 * 背包问题
 *
 * ->思考如何记录具体的值
 */
public class BagProblem {
     /**
     * @Param 背包容量
     */
     final int V = 10;
     /**
     * @Param 物品对应容量
     */
     final int [] c ={2,1,4,3,5,7};
     /**
     * @Param 物品对应价值
     */
     final int [] w ={4,3,6,5,17,9};


     /**
     * 01背包问题
     */
     public void fun1(){
         int [][] dp = new int [c.length+1][V+1];
         List<Integer> selectList = new ArrayList<>();
         Arrays.fill(dp[0],0);//未选择物品时的价值
         for(int i=1;i<=c.length;i++){// 考虑选择第i个物品时的决策
             for(int j=1;j<=V;j++){// 当背包容量为j时对应第i个物品的选择

                 if(j-c[i-1]<0){// 说明容量不够,只能选择放弃选择该物品
                     dp[i][j] = dp[i-1][j];
//                     if(selectList.contains(i)){
//                         selectList.remove((Object)i);
//                     }
                 }else{
                     // 注意容量 c,w的下标对应
                     dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-c[i-1]]+w[i-1]);// 比较在j容量下前i-1个物品价值和 选择i物品只剩
                     if(dp[i-1][j]<dp[i-1][j-c[i-1]]+w[i-1])
                        selectList.add(i);
                 }
             }
         }

         for(int i=0;i<=c.length;i++){
             for(int j=0;j<=V;j++){
                 System.out.print(dp[i][j]+" ");
             }
             System.out.println();
         }
         System.out.println("-------record----------");
        // selectList.stream().forEach(System.out::println);

         //int []used = new int[c.length+1];
         List<Integer> record = new ArrayList<>();

         // 开始进行倒退
         int v = V;
         for(int i=c.length;i>0;i--){
             if(dp[i][v]!=dp[i-1][v]){
                 record.add(i);
                 v-=c[i-1];
             }
         }
         record.stream().forEach(e-> System.out.print(e+" "));

     }


     /*考虑选取几个,在选取几个中最大定位该处的dp值*/
    /**
     * 完全背包问题
     */
    void fun2(){
         int [][]dp = new int[c.length+1][V+1];
         Arrays.fill(dp[0],0);

         for(int i=1;i<=c.length;i++){
             for (int j=1;j<=V;j++){
                 int k=0;//选取当前第i个物品的数量,以牺牲背包容量来选取添加该物品的价值

                 int max=dp[i-1][j-0*c[i-1]]+0*w[i-1];//也就是不选,为了更好理解特意写上0*。。

                 // 寻找0...k最大价值
                 while(j-k*c[i-1]>=0){// 保证书包容量够用
                     if(dp[i-1][j-k*c[i-1]]+k*w[i-1]>max){
                         max = dp[i-1][j-k*c[i-1]]+k*w[i-1];
                     }
                     k++;
                 }
                 dp[i][j]=max;

             }
         }

         for(int i=0;i<=c.length;i++){
             for(int j=0;j<=V;j++){
                 System.out.print(dp[i][j]+" ");
             }
             System.out.println();
         }


        int v = V;
         List<Integer> record = new ArrayList<>();
        for(int i=c.length;i>0;i--){
            if(dp[i][v]!=dp[i-1][v]){
                record.add(i);
                v-=c[i-1];
            }
        }
        record.stream().forEach(e-> System.out.print(e+" "));


     }

    /**
     * 判断是否能够恰好填满(无限物品与有限物品)
     *
     *     1   2   3   4   5   6   7   8
     *     0   0
     * 1   0   1   1   2   2   3   3   4
     *
     * 2   1   2
     * 3
     * 4
     *
     *
     *
     *
     */

     // 只有一个,那么来一个回溯，dfs>
     // [2,3,1,4] , 10
     boolean flag=false;
     void dfs(int target,int val,int []nums,int pos){
         if(target==val){
             System.out.println("TEST--YES");
             flag = true;
             return;
         }
         if(pos==nums.length){
             return;
         }
         dfs(target,val+nums[pos],nums,pos+1);
         dfs(target,val,nums,pos+1);//回退
     }

     public static void main(String[]args){

//         Set<Integer>[]set = new HashSet[10];//<Integer>()
//         set[0].add(1);
//
//         HashSet<Integer>[] set = new HashSet[100];
//         set[0].add(1);

         ArrayList<String> name[] = new ArrayList[9];
         name[0].add("43");




//         new BagProblem().fun1();
//         System.out.println("---------------------");
//         new BagProblem().fun2();
//
//         int[][] trs = {{1,2}};
//         new BagProblem().findJudge(2,trs);
//         int []nums={2,3,0,4};
//         BagProblem bp = new BagProblem();
//         bp.dfs(10,0,nums,0);
//         if(bp.flag){
//             System.out.println("YES");
//         }else{
//             System.out.println("NO");
//         }
     }

    public int findJudge(int n, int[][] trust) {

        int[] mayPerson =new int[n+1];
//        Set[] sets = new HashSet[n+1];

          //List<HashSet<Integer>> list = new ArrayList<>();
          Map<Integer,Set<Integer>> map = new HashMap<>();
//        sets[0].add(1);

        for(int i=0;i<trust.length;i++){
            mayPerson[trust[i][0]]=1;// 若等于1则不可能了
            if(map.get(trust[i][1])==null){
                Set<Integer> set = new HashSet();
                set.add(trust[i][0]);
                map.put(trust[i][1],set);
            }else{
                map.get(trust[i][1]).add(trust[i][0]);
            }


            //list.get(trust[i][1]).add(trust[i][0]);
           // sets[trust[i][1]].add(trust[i][0]);// i+1序号被相信的人
        }

        int flag=0;
        int m=0;//该位子为法官
        for(int i=1;i<=n;i++){
            if(mayPerson[i]==0){
                m = i;
                flag++;
            }
        }
        if(flag!=1)
            return -1;

        if(map.get(m).size()==n-1)   { //sets[m]
            return m;
        }else {
            return -1;
        }

    }


}
