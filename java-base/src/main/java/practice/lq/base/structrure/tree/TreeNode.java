package practice.lq.base.structrure.tree;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 */
public class TreeNode {

    private String data;
    private TreeNode lChild;
    private TreeNode rChild;

    public TreeNode(String data, TreeNode lChild, TreeNode rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public TreeNode() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getLChild() {
        return lChild;
    }

    public void setLChild(TreeNode lChild) {
        this.lChild = lChild;
    }

    public TreeNode getRChild() {
        return rChild;
    }

    public void setRChild(TreeNode rChild) {
        this.rChild = rChild;
    }
}
