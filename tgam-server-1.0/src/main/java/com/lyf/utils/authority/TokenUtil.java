package com.lyf.utils.authority;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

/**
 * Description  Token生成工具
 * header + payload + signature
 *
 * AUTHOR lyf
 *
 *流程分析：
 * 1、此类生成一个token（包括header+payload+signature）,返回到客户端,并将此token的签名
 * 放入Redis或者MySQL数据库
 * 2、客户端发送数据带token(包含header和payload)
 * 3、检验token 以防伪造,解析token为三部分,检查header和payload生成的签名是否和发来的一致
 * 4、检查数据库或者缓存是否存在此签名
 *
 */

public class TokenUtil {

    public static final String TOKEN_AES_KEY ="";


    public static String getToken(String userId,String password) throws UnsupportedEncodingException {
        String token = "";
        token = JWT.create().withAudience(userId).sign(Algorithm.HMAC256(password));
        return token;
    }

    /*
    对应AuthenticationInterceptor
     // 获取 token 中的 user id
                String userId;

                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }

                User user = userMapper.queryByUserId(Long.parseLong(userId));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
     */


}
