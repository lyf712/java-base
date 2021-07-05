package com.lyf.sercurity;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author LYF
 * @desc 请求数据解密
 * @date 2021-01-28
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecurityParameter {

       //  @interface自定义注解
        /**
         * 入参是否解密，默认解密
         */
        boolean inDecode() default true;

        /**
         * 出参是否加密，默认加密
         */
        boolean outEncode() default true;

}
