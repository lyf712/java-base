package practice.lq.questions.consolidate.dp.labuladong.tree;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/15
 * @VERSION 1.0
 * @DESC
 */
public class TestFab1 implements Runnable{

    private int method;//方法选择，其实直接判断也行，但为了应用反射，我便写了一个简单的反射调用

    public TestFab1(int method) {
        this.method = method;
    }

    @Override
    public void run() {

        //System.out.println("这里是");
        TreeLeetCodePart1 treeLeetCodePart1 = new TreeLeetCodePart1();
        for(int i=10;i<1000;i*=2){
            long start = System.currentTimeMillis();
            int val = ReflectUtil.executeMethod(method,i);//treeLeetCodePart1.method4(i)
            long end = System.currentTimeMillis();
            System.out.println("方法"+method+"求解"+i+"所用时间为:"+(end-start)+";结果为"+val);
        }

    }


}
