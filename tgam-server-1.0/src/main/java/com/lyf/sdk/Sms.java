package com.lyf.sdk;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lyf.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @AUTHOR LYF
 * @DATE
 * @DES
 * 阿里云短信服务调用
 */

public class Sms {
    Logger logger =  LoggerFactory.getLogger(Sms.class);

    public void sendSms(String phone,String code){

        logger.info("于"+ TimeUtil.getTime()+"发送短信"+code+"至"+phone);
        System.out.println("于"+ TimeUtil.getTime()+"发送短信"+code+"至"+phone);

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G1Bf13hqBWjnGYysjSx", "HmAr7ahojks3vr0hpAdh0Ne4y4Sohf");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "健康云小平台");
        request.putQueryParameter("TemplateCode", "SMS_205883913");
        request.putQueryParameter("TemplateParam", code);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}