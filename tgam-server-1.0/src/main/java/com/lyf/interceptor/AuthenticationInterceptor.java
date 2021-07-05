package com.lyf.interceptor;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.lyf.dao.mapper.UserMapper;
import com.lyf.sercurity.LoginToken;
import com.lyf.sercurity.PassToken;
import com.lyf.utils.authority.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @AUTHOR LYF
 * @DATE 2021-2-1
 * 拦截器配置
 *
 */

public class AuthenticationInterceptor implements HandlerInterceptor {

    /*调用controller之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // token应该放在header
        String token = request.getHeader("token");//从header中获取token

        String deviceType = request.getHeader("User-Agent");
        System.out.println("拦截器收获"+deviceType);
        // handler??
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod =(HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 检查是否有PassToken注解，有则跳过
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        // 检查有无需要用户权限的注解
        if(method.isAnnotationPresent(LoginToken.class)){
            LoginToken loginToken = method.getAnnotation(LoginToken.class);

            // 执行认证
            if(loginToken.required()){

                if (token == null){
                   // return resp;
                  throw new RuntimeException("无token,请重新登录");
                }

                try{

                    Claim username = JwtUtil.parseToken(token).get("userName");
                    Claim password = JwtUtil.parseToken(token).get("password");
                    /*跳到shiro登录？*/
                    /*解析不出username,password?可能存在JWT生成的token可以解析但是不能解析出来吗*/
                    if(username==null||password==null){
                        throw new RuntimeException("无使用该系统权限");
                    }else {

                        String userName = username.asString();
                        String pwd = password.asString();

                        System.out.println("拦截器获取"+userName+pwd);
                        /*判断用户名和密码是否为空*/
                        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd)) {
                            throw new RuntimeException("用户和密码有误");
                        }

                        //用户认证信息
                        Subject subject = SecurityUtils.getSubject();

                        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                                userName,
                                pwd
                        );

                        try {
                            //进行验证，这里可以捕获异常，然后返回对应信息
                            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
                        } catch (UnknownAccountException e) {
                            throw new RuntimeException("用户名不存在");
                        } catch (AuthenticationException e) {
                            throw new RuntimeException("账号或密码错误");
                        } catch (AuthorizationException e) {
                            throw new RuntimeException("无该API权限");
                        }


                    }
                }catch (JWTDecodeException e){
                    throw new RuntimeException("无使用该系统权限");
                }

                try {
                    JwtUtil.verifyToken(token);
                }catch (Exception e){
                    throw new RuntimeException("凭证已过期,请重新登录");
                }


                return true;
            }
        }
        return true;
    }

    /*controller执行后，视图渲染之前*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /*视图渲染完成后,一些清理工作*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
