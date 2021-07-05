package practice.lq.base.structrure.tree;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * 一、基本操作
 * 1.创建
 * （1）层次构造
 * （2）平衡二叉树
 *
 * 2.遍历（先、中、后、层次）
 *
 */
public class OperateTree {

    static private String[] testData= new String[10];
    static {
        for(int i = 0;i<testData.length;i++){
            testData[i]="I am data"+(i+1);
        }
    }


    void createTree(TreeNode T,String[]data){

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(T);

        for(int i =0;i<data.length;i++){

            TreeNode insertNode = new TreeNode(data[i],null,null);

            while(q.peek()!=null){
                if(q.peek().getLChild()==null){
                    q.add(insertNode);//
                    q.peek().setLChild(insertNode);
                    break;//插入之后需要跳出
                }else if(q.peek().getRChild()==null){

                    q.add(insertNode);
                    q.peek().setRChild(insertNode);
                    break;
                }else {
                    q.poll();//出队列，进行下一个节点
//                    break;
                }
            }


        }

//        for(int  i = 0;i<data.length;i++){
//            // 待插入节点
//            TreeNode insertNode = new TreeNode(data[i],null,null);
//            // 进行插入(按层次构造）
//            Queue<TreeNode> q2 = new ArrayDeque<>();
//            q2 =q;
//            TreeNode temp = new TreeNode();
//
//            while((temp=q2.poll())!=null)
//            {
//                if(temp.getLChild()==null)
//                {
//                    temp.setLChild(insertNode);
//                    q.add(temp);
//                }else if(temp.getRChild()==null)
//                {
//                    temp.setRChild(insertNode);
//                    q.add(temp);
//                }
//            }
//        }

        // 测试

        preTraverse(T);

    }

    // 先序遍历（递归版）
    void preTraverse(TreeNode tree){

        if(tree!=null){
            System.out.println(tree.getData());
            preTraverse(tree.getLChild());
            preTraverse(tree.getRChild());
        }else{
            return;//递归终止
        }

    }


    public static void main(String[]args){
        OperateTree opt  = new OperateTree();
        TreeNode tree = new TreeNode("I am root",null,null);

//        tree.setLChild(new TreeNode("data1",null,null));
//        tree.setRChild(new TreeNode("data2",null,null));
//
//        tree.getLChild().setLChild(new TreeNode("data3",null,null));
//        tree.getLChild().setRChild(new TreeNode("data4",null,null));

//        opt.preTraverse(tree);

        opt.createTree(tree,testData);

    }


}
