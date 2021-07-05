package practice.lq.questions.consolidate.dp.labuladong.review;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 *
 * 数结构
 *
 */

// 二叉树，有必要使用getter\setter吗
class TreeNode{
    int val;
    TreeNode left,right;

    void traverse(TreeNode t){
        if(t==null){
            return;//停止
        }
//      System.out.println(t.val);
        traverse(t.left);
        System.out.println(t.val);
        traverse(t.right);
    }


}

class NTreeNode{
    int val;
    NTreeNode[] children;

    void traverse(NTreeNode root){
        if(root==null){
            // 进行递归终点处理结果，可以设置集合，list进行路径或者其他记录

        }
        for(int i =0;i<root.children.length;i++){// 进行选择递归
            traverse(root.children[i]);
        }
    }

}

// N叉数的遍历


public class TreeStructure {


    public static void main(String[]args){
        TreeNode treeNode = new TreeNode();
        treeNode.val=10;

        TreeNode treeNode1 = new TreeNode();
        treeNode1.val=4;

        treeNode.left=treeNode1;
//        treeNode.right=null;

        treeNode.traverse(treeNode);

    }
}
