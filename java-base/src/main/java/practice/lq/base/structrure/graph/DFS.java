package practice.lq.base.structrure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 类似先序遍历，指定一个方向进行搜索，不能搜索则回退，搜索到值则停止，并打印
 *
 */
public class DFS {

    static class Node {
        String name = null;
        Integer no = null;
        boolean visited = false;
        List<Node> vex = null;

        public Node(String name, Integer no, boolean visited, List<Node> vex) {
            this.name = name;
            this.no = no;
            this.visited = visited;
            this.vex = vex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNo() {
            return no;
        }

        public void setNo(Integer no) {
            this.no = no;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public List<Node> getVex() {
            return vex;
        }

        public void setVex(List<Node> vex) {
            this.vex = vex;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", no=" + no +
                    ", visited=" + visited +
                    ", vex=" + vex +
                    '}';
        }
    }


//    static Node initNode = new Node("A",1,false,null);

    // 深度优先，选择一个方向进行访问，直到无法访问则进行回退，回退时，该节点应该恢复？回溯+递归？
    // 如何回退？？，设置一个前驱？

    void dfs(Node node){//从起点进行搜索

        if(node.name=="C"){
            // 搜索到目标值，即可退出
            System.out.println("可搜索到");
        }else{
           // 若遇见无法极限搜索则回退
            List<Node> nextList = new ArrayList<>();//临边
            nextList = node.vex;

            for(Node n:nextList){
                if(n.visited==false){
                    n.visited=true;//访问
                    dfs(n);
                }else{

                }

            }

        }
    }
    public static void main(String[]args){

        Node initNode =new Node("A",1,false,null);


    }
}
