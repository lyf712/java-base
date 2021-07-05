package practice.lq.questions.country_2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/22
 * @VERSION 1.0
 * @DESC
 * 2.扩散（填空题）
 * 20312088
 * 问题记录，在x和i的使用上出问题混用导致排查了十分钟左右，花费时间20min左右
 */
public class T2 {
    int[][] point = {
            {2020,2020},
            {4040,2031},
            {2031,2034},
            {4020,4020}
    };


    class Node{
        int x,y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // 16164
    void test(){
        boolean[][]box = new boolean[6061][6041];
        for(int i= 0;i<6061;i++){
            Arrays.fill(box[i],false);
        }

        Set<Node> set = new HashSet<>();

        int count=0;
        int len= point.length,counter=0;
        while(counter<len){
            int x=point[counter][0];
            int y=point[counter][1];
            for(int i = x-2020;i<=x+2020;i++){
                int abs=2020-Math.abs(x-i);
                //System.out.println(abs);
                    for(int k=-abs;k<=abs;k++){
                        box[i][y+k]=true;
                        //set.add(new Node(i,y+k));
                        count++;
                       // System.out.println("计数:("+i+","+(y+k)+")");
                    }
            }
            counter++;
        }

        int sum=0;
        for(int i= 0;i<6061;i++){
            for(int j=0;j<6041;j++){
                if(box[i][j]==true)
                    sum++;
            }
        }

        System.out.println("rs:"+sum+";"+count);
    }
    public static void main(String[]args){
        T2 t2 = new T2();
        t2.test();
    }

}
