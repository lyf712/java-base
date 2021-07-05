package com.lyf.utils.authority;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    //密钥
    public static final String SECRET = "fadsfaskj;12dslk";
    //过期时间:秒
    public static final int EXPIRE = 600000;// 设置token时效

    /**
     * 生成Token
     * @param password
     * @param userName
     * @return
     * @throws Exception
     */

    public static String createToken(String userName, String password) throws Exception {

        // 现在时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        // 希望的日期,也就失效的日期
        Date expireDate = nowTime.getTime();
        System.out.println(nowTime.getTime());

        Map<String, Object> map = new HashMap<>();

        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)//头
                .withClaim("userName", userName)
                .withClaim("password", password)
                .withSubject("TGAM验证")//
                .withIssuedAt(new Date())//签名时间
                .withExpiresAt(expireDate)//过期时间
                .sign(Algorithm.HMAC256(SECRET));//签名
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    //
    public static  Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            //return "凭证已过期，请重新登录";
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
       // return jwt.getToken();
    }

    /**
     * 解析Token
     * @param token
     * @return
     */

    public static Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }

}
