package practice.lq.questions.country_2020;


import java.util.HashSet;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/18
 * @VERSION 1.0
 * @DESC
 * 1.美丽的2
 * 2.
 */

public class GapFill {

    /*美丽的2*/
    //563
    static void test1(){
        int counter =0;
        for(int i=1;i<=2020;i++){
            String temp = i+"";
            if(temp.contains("2")){
                counter++;
                System.out.println(temp);
            }
        }
        System.out.println("rs:"+counter);
    }

    /*扩散*/
    //确定上下限，遍历n^2 （6000*6000次）
    // 中心正方形手算
    static class Point{
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    static void test2(){
//        boolean [][]arr = new boolean[6040][6061];
//        for(int i =0;i<6040;i++)
//            Arrays.fill(arr[i],false);

        // (0,0)=> (2020,2020)
        // (2020,11)=> (4040,2031)
        // (11,14)-> (2041,2034)
        // (2000,2000)->(4020,4020)

        // arr[][]的作用只是判断是否重合了，list记录
        // 已有的point
        Set<Point> set = new HashSet<>();
        set.add(new Point(2020,2020));
        set.add(new Point(4040,2031));
        set.add(new Point(2041,2034));
        set.add(new Point(4020,4020));
//        arr[2020][2020]=true;
//        arr[4040][2031]=true;
//        arr[2041][2034]=true;
//        arr[4020][4020]=true;

        int dicX[]={0,1,0,-1};
        int dicY[]={1,0,-1,0};

        Set<Point> tempSet= new HashSet<>();
        // 第i次扩散
        for(int i =1;i<=2020;i++){

//            // 直接tempSet = set,就是引用的会冲突
//
            for(Point p:set){
                tempSet.add(p);
            }

//            Point[] points= (Point[]) set.toArray();
            for(Point p:tempSet){
                for(int k =0;k<4;k++){
                      int x = p.x+dicX[k];
                      int y = p.y+dicY[k];
                          set.add(new Point(x,y));
                }
            }

            tempSet.clear();

//            for(int i =0;i<set.size();i++){  //Point p:set
//                for(int j =0;j<4;j++){
//                      int x = p.x+dicX[j];
//                      int y = p.y+dicY[j];
////                      if(!arr[x][y])
//                          set.add(new Point(x,y));
//                }
//            }
        }
        System.out.println("rs:"+set.size());
    }

    /*阶乘约数  =>约数总结*/
    // 就是数的分解（遍历1-100每个数的分解约数数量累加即可）
    //240
    static int countNum(int num){
        int counter = 1;
        for(int i=2;i*i<=num;i++){
            if(num%i==0){
                counter++;
                num/=i;
                i=1;//重新开始计
            }
        }
        return counter;
    }
    static void test3(){
        int counter=0;
        for(int i=1;i<=100;i++){
            int temp =countNum(i);
            counter+=temp;
            System.out.println("当前为"+i+";该数约数为"+temp+";总数累计为"+counter);
        }
    }

    /*本质上升序列*/
    static void test4(){
        // 200个字符
        String inp="tocyjkdzcie" +
                "oiodfpbgcncsrjbhmugdno" +
                "jjddhllnofawllbhfiadgdcdjstemphmnj" +
                "ihecoapdjjrprrqnhgccevdarufmliqijgihhfgdcm" +
                "xvicfauachlifhafpdccfseflcdgjncadfclvfmadvrnaaahahnds" +
                "ikzssoywakgnfjjaihtniptwoulxbaeqkqhfwl";
        // 暴力求解思维
    }

    public static void main(String[]args){
        test3();
    }
}
