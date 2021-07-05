package com.lyf.utils;

public  class HandleJson {

    public static  String handleJson(String dataArr){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0;i<dataArr.length();i++){
            if(dataArr.charAt(i)!='p'){stringBuilder.append(dataArr.charAt(i));}
            else {
                stringBuilder.append("p="+"'"+dataArr.substring(i+2,i+21)+"'");
                i+=20;
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString().replace("=",":");
    }
}
