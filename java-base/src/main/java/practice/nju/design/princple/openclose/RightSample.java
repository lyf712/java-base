package practice.nju.design.princple.openclose;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/5
 * @VERSION 1.0
 * @DESC
 */

// 考虑使用接口更好？？
abstract class AbstractChart{
    void display() {

    }
}

class PipeChart1 extends AbstractChart{

}

class ChartDisplay1{

    private AbstractChart chart = null;

    void setChart(AbstractChart
                  abstractChart){
        chart = abstractChart;
    }

    void displayChart(){
        chart.display();
    }

}


public class RightSample {
}
