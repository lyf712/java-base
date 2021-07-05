package practice.lq.completation.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/31
 * @VERSION 1.0
 * @DESC
 */



 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }


public class T3 {

    // void 型，利用外部进行记录所求值，
    int res = 0;
    public void handle(TreeNode root, int low, int high) {
        if(root==null){
            // 此次递归结束，终止条件
            return;
        }
        if(root.val<low){//
            handle(root.right,low,high);
        }
        if(root.val>high){
            handle(root.left,low,high);
        }
        if(root.val>=low&&root.val<=high){
            res+=root.val;//进行累加
            handle(root.left,low,high);//向左递归
            handle(root.right,low,high);//向右递归
        }
    }
    public int rangeSumBST(TreeNode root, int low, int high) {
        handle(root,low,high);
        return res;
    }


    TreeNode temp = new TreeNode();
    TreeNode p=temp;
    List<TreeNode> list = new ArrayList<>();

    void handle(TreeNode root){
        if(root.left==null&&root.right==null){//记录递归终点，也可以用来查询叶子节点
            System.out.println(root.val+"____");
            list.add(root);
            p = root;
            p = p.right;
            return;
        }else{
            if(root.left!=null)
            handle(root.left);

            list.add(root);//回退点
            p = root;
            p = p.right;

            if(root.right!=null)
            handle(root.right);
        }
    }
    public TreeNode increasingBST(TreeNode root) {
        p = temp;
        handle(root);
        TreeNode treeNode = new TreeNode(list.get(0).val);
        TreeNode p1 = treeNode;
        for(int i=1;i<list.size();i++){
            p1.right = list.get(i);
            p1 = p1.right;
                    //new TreeNode(list.get(i).val);
        }
//        list.stream().forEach(e->{
//            System.out.println(e.val+"->");
//        });


        //
        System.out.println("--p2--");
        TreeNode p2 = treeNode;
        while(p2!=null){
            System.out.print(p2.val+"-->");
            p2=p2.right;
        }

        System.out.println("--p3--");
        TreeNode p3 = temp;
        while(p3!=null){
            System.out.print(p3.val+"-->");
            p3=p3.right;
        }

        return temp;
    }
    public static void main(String[]args){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(6);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.right = new TreeNode(8);
        treeNode.right.right.left = new TreeNode(7);
        treeNode.right.right.right = new TreeNode(9);
        TreeNode treeNode1 = new T3().increasingBST(treeNode);


       // new T3().increasingBST();
    }

}
