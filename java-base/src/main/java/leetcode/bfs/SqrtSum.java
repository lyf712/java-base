package leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/11
 * @VERSION 1.0
 * @DESC
 */
public class SqrtSum {


        // 广度搜索，先搜到的则为最少的
        int bfs(int n){
            Queue q = new LinkedList<Integer>();
            int temp = n;
            for(int i=temp;i>0;i--){ // 所有可能的
                if(Math.pow(Math.ceil(Math.sqrt(i)),2)==i)
                {
                    q.add(temp-i);// 加入剩余值的大小
                }
            }
            int level = 1;
            while(!q.isEmpty()){
                int levelSize = q.size();
                int count=0;
                while(count<levelSize){
                    Integer temp2 =  (Integer) q.poll();
                    if(temp2==0)
                        return level;//已经BFS搜到了

                    for(int i=temp2;i>0;i--){ // 所有可能的
                        if(Math.sqrt(i)*Math.sqrt(i)==i)
                        {
                            q.add(temp2-i);// 加入剩余值的大小
                        }
                    }
                    count++;
                }
                level++;
            }
            return n;
        }

        public int numSquares(int n) {
            return bfs(n);
        }
        public static void main(String[]args){
            new SqrtSum().bfs(12);
            //new ArrayList<>().
            System.out.println(Math.sqrt(11)+";"+(Math.pow(Math.ceil(Math.sqrt(9)),2)==9));
        }

}
