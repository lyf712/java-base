package practice.lq.questions.consolidate.domain;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/13
 * @VERSION 1.0
 * @DESC
 */
public  class Point{

    int i,j;// x,y对应col,row;容易错注意,因此还是采用计算机中i,j对应row,col
    Point pre;// 前面的一个点,为方便寻找最短路径，进行的记录
    int val;//是否可行，，是障碍物，还是？

    public Point() {
    }

    // 快捷键生成getter,setter
    public Point(int i, int j, Point pre, int val) {
        this.i = i;
        this.j = j;
        this.pre = pre;
        this.val = val;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Point getPre() {
        return pre;
    }

    public void setPre(Point pre) {
        this.pre = pre;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Point{" +
                "i=" + i +
                ", j=" + j +
                ", pre=" + pre +
                ", val=" + val +
                '}';
    }
}