package learn.design.prototype;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/1
 * @VERSION 1.0
 * @DESC
 * 原型模式，克隆复制
 */
class ProtoModel implements Cloneable{
    public ProtoModel(){
        System.out.println("构造ProtoModel");
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("复制ProtoModel");
        return (ProtoModel)super.clone();
    }
}

public class ProtoTypeModel {


    public static void main(String[]args) throws CloneNotSupportedException {
        ProtoModel protoModel1= new ProtoModel();
        ProtoModel protoModel2=(ProtoModel)protoModel1.clone();
        if(protoModel1==protoModel2){
            System.out.println("相同");
        }else {
            System.out.println("不同");
        }

    }


}
