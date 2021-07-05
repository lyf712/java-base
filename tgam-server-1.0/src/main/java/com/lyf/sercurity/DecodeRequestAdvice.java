//package com.lyf.sercurity;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.lyf.utils.encrypt.AESEncryptUtil;
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
///**
// * @AUTHOR LYF
// * @DATE 2021-01-28
// * @DESC
// * 请求参数解密
// * 参考：https://blog.csdn.net/baidu_38990811/article/details/83385277
// *
// * 知识点总结
// * toString()和new String()
// * byte[] 和string
// *
// * 网络编程  HTTP报文
// *
// */
//
//@ControllerAdvice(basePackages = "com.lyf.controller")
//public class DecodeRequestAdvice implements RequestBodyAdvice {
//
//    // 日志
//    Logger logger = LoggerFactory.getLogger(DecodeRequestAdvice.class);
//
//
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return true;
//    }
//
//    // body读取之前加工？加工流对象 ,改为JSONObject
//
//    @Override
//    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
//
//
//        try{
//            // 判断是否进入加密
//            if(methodParameter.getMethodAnnotation(SecurityParameter.class).inDecode()){
//
//                byte[] readContent = new byte[1024];
//                // 读入字节流
//                httpInputMessage.getBody().read(readContent);
//
//                String content = new String(readContent);
//
//                System.out.println(AESEncryptUtil.decrypt(content));
//
//                return (HttpInputMessage) IOUtils.toInputStream(AESEncryptUtil.decrypt(content));
//            }
//        }catch (Exception e){
//        }
//        return httpInputMessage;
//    }
//
//    @Override
//    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//
//        System.out.println("进入afterBodyRead");
//
//
//
//        return o;
//    }
//
//    @Override
//    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return o;
//    }
//}
