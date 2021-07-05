package practice.os.initmate.resources.memerory;

/**
 * @AUTHOR LYF
 * @DATE 2021/3/30
 * @VERSION 1.0
 * @DESC
 */
public interface Memory {
   static int capacity = 1024;// 默认1024
   static int[] record = new int[capacity];
//    static {
//        Arrays.fill(record,0);//
//    }

   int read(int addr);
   boolean write(int addr,int content);//只能进行01的读取

   boolean encode(int addr);//权限管理，设置该处地址的权限限制
   boolean unCode(int addr);

}
