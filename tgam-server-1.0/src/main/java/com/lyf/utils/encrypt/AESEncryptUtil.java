package com.lyf.utils.encrypt;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @AUTHOR LYF
 * @DATE 2021-02-02
 *
 * AES加密工具
 *
 * 加密的原理
 * （1）KeyGenerator密钥生成器
 * （2）Chiper进行加密
 * （3）Base64进行编码 enCode
 *
 *
 */

public class AESEncryptUtil {

    //可配置到Constant中，并读取配置文件注入,16位,自己定义
    private static final String KEY = "Ww123321abcDFJaK";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {

        /* 密钥生成器  https://blog.csdn.net/kzcming/article/details/80095114*/
        KeyGenerator kgen = KeyGenerator.getInstance("AES");

        /*密钥初始化大小为128*/
        kgen.init(128);



        /*此类为加密和解密提供密码功 https://blog.csdn.net/u010142437/article/details/17785089*/
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);

        // 加密初始化,初始化加密类chiper(mode和钥匙  )  new SerreKeySpec构造密钥
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        byte[] b = cipher.doFinal(content.getBytes("utf-8"));

        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));

        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);

        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }
    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }


    public static void main(String[] args) throws Exception {


        JSONObject req = new JSONObject();
        req.put("data","[1,2,3,4,5,6]");
        req.put("info","test");

        String str = encrypt(req.toJSONString());

        System.out.println("加密后："+str);
        System.out.println("解密后："+decrypt(str));

    }

}
