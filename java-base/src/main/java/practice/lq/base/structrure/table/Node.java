package practice.lq.base.structrure.table;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 * Node节点
 */
public class Node {

    private String data;// 数据还可以采用一个类进行存储，或者集合
    private Node next;// 指针域，指向下一个

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
    }
}
