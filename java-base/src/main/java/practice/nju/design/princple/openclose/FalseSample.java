package practice.nju.design.princple.openclose;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/5
 * @VERSION 1.0
 * @DESC
 * 假设一个
 *
 */

class PipeChart{
    static void display(){
        System.out.println("展示PipeChart");
    }
}

class BarChart{
    static void display(){
        System.out.println("展示BarChart");
    }
}

// 如需添加新的图表，则需要在此处进行拓展，破坏开闭原则
class ChartDisplay{
    void display(String type){

        if(type.equals("pipeChart")){
            PipeChart.display();
        }else if("BarChart".equals(type)){
            BarChart.display();
        }

    }
}


public class FalseSample {
}
