package learn.design.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/2
 * @VERSION 1.0
 * @DESC
 */

abstract class Subject{
    //private
    protected List<Observer> observerList = new ArrayList<>();
    protected List<String> msgList =new ArrayList<>();
    void addObserver(Observer observer){
        observerList.add(observer);
    }
    void remove(Observer observer){
        observerList.remove(observer);
    }

    void publicMsg(String msg)
    {
        msgList.add(new Date()+":"+msg);
    }

    abstract void notifyObserver();
}

class concreteSubject extends Subject{

    @Override
    void notifyObserver() {
        System.out.println("收到新消息,通知所有观察者(订阅者)");
        for(Observer observer:observerList){
            observer.response(msgList.get(msgList.size()-1));
        }
    }
}


// 为什么是接口而不是抽象类？，是满足该规则，并非拓展或者实现部分
interface  Observer{
    void response(String receiveMsg);
}

class Observer1 implements Observer{

    @Override
    public void response(String receiveMsg) {
        System.out.println("Observer1观察到目标改变,收到信息--"+receiveMsg);
    }
}

class Observer2 implements Observer{

    @Override
    public void response(String receiveMsg) {
        System.out.println("Observer2观察到目标改变,收到信息--"+receiveMsg);
    }
}
public class ObserverModel {
    public static void main(String[]args){
        Subject subject = new concreteSubject();
        Observer observer1 = new Observer1();
        Observer observer2 = new Observer2();
        subject.addObserver(observer1);
        subject.addObserver(observer2);


        // 当发布消息才触发通知
        subject.publicMsg("hello,this is a test!!");
        subject.notifyObserver();
        subject.remove(observer1);
        subject.publicMsg("hello,the notice ! observer1 has been removed!");
        subject.notifyObserver();


    }
}
