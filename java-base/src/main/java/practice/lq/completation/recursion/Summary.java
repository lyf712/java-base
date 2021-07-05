package practice.lq.completation.recursion;

import org.apache.poi.hssf.record.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 */
public class Summary {
    /**
     * @param n 传入参数
     * @param pos 当前递归位置
     * @param val 计算参数,子集的异或问题参考
     * @param flag 状态判断,N皇后问题
     * @return
     */
    T recursion(int n,int pos,int val,int []flag){ // flag可以boolean型
        T t = new T();// 返回参数,根据需要声明设定
        if(pos==flag.length){ // 终止条件,根据题目来定
            // 处理
            return t;//终止
        }
        if(flag[pos]==1){// 根据当前状态的情况进行处理
            // 处理
        }
        // 递归进行
        // 标志位处理
        flag[pos] = 1;
        recursion(n,pos+1,val*n,flag);
        flag[pos] = 0;//标志位回退，--> 回溯模板
        recursion(n,pos+1,val*n,flag);
        return t;
    }

    List<String> record = new ArrayList<>();
    // 树的遍历结构
    void dfs(TreeNode treeNode,int n,int pos){
        if(treeNode==null){
            // 递归一次结束进行记录
           // record.add(treeNode.val+"");
            //  record进行记录
            return;//终止
        }
        // 进行处理数据treeNode
        dfs(treeNode.left,n,pos+1);//向左
        dfs(treeNode.right,n,pos+1);//向右
    }




}
