package practice.offer;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 */
class BNode{
    String val;
    BNode left;
    BNode right;

    public BNode(String val) {
        this.val = val;
    }
}
public class ReBuildBTree {
    private String preSeq = "ABEDCF";
    private String midSeq = "EBDACF";
    private BNode tree;

    // 递归的思维，先将左边的根节点排好，再排右边
    void handle(){

    }
}
