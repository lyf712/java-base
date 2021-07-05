package learn.design.proxy;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/2
 * @VERSION 1.0
 * @DESC
 * spring 的切片？？
 * 若需对某个服务进行调用，但是不直接进行，通过中介代理进行调用。
 */

// 抽象主题
interface Subject{
    void request();
}

class RealSubject implements Subject{
    //private String name;

    @Override
    public void request() {
        System.out.println("真实实现主题....");
    }

}

class SubjectProxy{
    private Subject subject = null;

    public SubjectProxy(){
        if(subject==null){
            subject = new RealSubject();//生成代理的服务
        }
    }

    public void request(){
        System.out.println("进入请求");
        preRequest();
        subject.request();
        afterRequest();
    }

    private void preRequest(){
        System.out.println("进行对subject的预处理");
    }
    private void afterRequest(){
        System.out.println("进行对subject的事后处理");
    }
}

public class ProxyTest {
    public static void main(String[]args){
        SubjectProxy subjectProxy = new SubjectProxy();
        subjectProxy.request();;
    }

}
