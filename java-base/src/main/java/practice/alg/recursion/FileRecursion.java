package practice.alg.recursion;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/29
 * @VERSION 1.0
 * @DESC
 */
public class FileRecursion {


    /**
     * 文件删除递归
     * 1:检测文件夹是否存在，不存在则退出
     * 2:获取该文件夹目录【获取文件数组】，遍历文件数组
     * 3:判断是文件则删除，不是则回调 2
     * 4.文件删除完后，将该文件夹删除
     */
    String path = "E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\alg\\recursion\\temp";
    File file = new File(path);

     void recursionDeleteDir(File file){

         try {
                       //String.valueOf(new FileInputStream(path)))
             // 不存在则退出
             if(!file.exists())
                 return;
             else{
                File[] files = file.listFiles();
                System.out.println("文件数量:"+Arrays.stream(files).count());
                file.delete();
                for(int i=0;i<files.length;i++){
                    System.out.println(files[i].getParent()+";"+files[i].getAbsolutePath()+";"+files[i].getPath());
                    String[] strings = files[i].getAbsolutePath().split("\\\\");
                    System.out.println(strings[strings.length-1]);
                    if(files[i].isFile()){
                        files[i].delete();
                    }else {
                        //files[i].delete();
                        recursionDeleteDir(files[i]);
                    }

                    //new File(files[i].getParent()
                    //files[i].renameTo(new File("E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\alg\\recursion\\temp2"+"\\"+strings[strings.length-1]));
                }

             }
         }catch (Exception e){
             e.printStackTrace();
         }

     }

    /*文件递归查询*/
    /**
     * 文件查询递归
     * <p>
     * 1:检测文件夹是否存在，不存在则退出
     * 2:获取该文件夹目录【获取文件数组】，遍历文件数组
     * 3:判断是文件夹则回调 2  ，如果是是文件且文件名后缀是 .java 则打印绝对路径
     */
    void recursionQuery(File f){
        if (!f.exists()) {
            System.out.println("该文件夹不存在");
            return;
        }
        //获取该文件夹的目录文件数组
        File[] files = f.listFiles();
        //遍历
        if (files != null) {
            for (File mf : files) {
                System.out.println(mf.getName());
                //如果是文件夹
                if (mf.isDirectory()) {
                    //递归
                    recursionQuery(mf);
                }

                //如果是文件 且文件名后缀是 .java 则打印觉得路径
                else if (mf.isFile() && mf.getName().endsWith(".java")) {
                    //删除文件,方法.delete() 删除成功返回true ,失败为false
                    System.out.println("文件" + mf.getName() + "绝对路径：" + mf.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[]args){
         //new FileRecursion().recursionDeleteDir(new FileRecursion().file);
        new FileRecursion().recursionQuery(new FileRecursion().file);
    }

}
