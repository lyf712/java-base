//package com.lyf.sercurity;
//
//import com.lyf.utils.encrypt.AESEncryptUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * @AUTHOR lYF
// * @DATE 2021 02-02
// * @DESC 返回信息加密
//
//advice和拦截器区别？？
// */
//
//@ControllerAdvice("com.lyf.controller.data")
//public class EncodeResponseAdvice implements ResponseBodyAdvice {
//
//    Logger logger = LoggerFactory.getLogger(ResponseBodyAdvice.class);
//
//    //需要true支持
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class aClass) {
//        return true;
//    }
//
//    /*在返回之前写入之前进行操作 */
//    /*
//    （1）判断是否有加密注解
//    （2）若有注解则进行加密，无则不加密
//
//
//     */
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        /* */
//        // 判断加密的情况
//        System.out.println("返回信息加密。。.");
//        if(methodParameter.getMethodAnnotation(SecurityParameter.class).outEncode()){
//
//            System.out.println("开始加密");
//            logger.info("开始加密...");
//
//            try {
//                return (Object) AESEncryptUtil.encrypt(body.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return body;
//    }
//}
