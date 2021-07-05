package com.lyf.sercurity;


import java.lang.annotation.*;

/*https://jingyan.baidu.com/article/ff42efa9c979e8c19e2202ce.html*/
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    boolean required()default true;
}
