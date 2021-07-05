package practice.utils.xlsx;

import jxl.Sheet;
import jxl.Workbook;
import java.io.File;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 * https://www.cnblogs.com/cx-code/p/9111336.html
 */
class Student{
    private String name;
    private Integer age;
    private String classes;
    private String no;

    public Student(String name, Integer age, String classes, String no) {
        this.name = name;
        this.age = age;
        this.classes = classes;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classes='" + classes + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}
public class TestXlsx {


    public static void main(String[]args){

        String excelFilePath ="E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\utils\\xlsx\\test2.xls";
//        File file = new File(excelFilePath);
//        file.renameTo(new File("E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\utils\\xlsx\\test2.xls"));

        try {
            // 工作文档
            Workbook rwb = Workbook.getWorkbook(new File(excelFilePath));
            // 工作表
            Sheet sheet = rwb.getSheet(0);

            Integer row = sheet.getRows();
            Integer col = sheet.getColumns();

            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    // 第一个是列数，第二个是行数
                    System.out.println(sheet.getCell(j,i).getContents());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
