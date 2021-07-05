package com.lyf.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;


import com.lyf.dao.domain.RecordData;
import com.lyf.dao.mapper.RecordDataMapper;
import com.lyf.task.recordcheck.CheckTimeoutScheduler;
import com.lyf.utils.result.ResultCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RecordDataMapper recordDataMapper;

    @Autowired
    CheckTimeoutScheduler checkTimeoutScheduler;

    @Test
    void contextLoads() {
          recordDataMapper.insertOneRecord("1",new RecordData());
    }

    /**
     * 测试响应码
     */
    @Test
    void testCode(){
       ResultCode resultCode= ResultCode.OK;
       System.out.println("testCode"+resultCode.getCode()+resultCode.getMsg());
    }

    @Test
    /**
     * 测试timestamp
     */

    void testTimeStamp(){

        String str = "[{\n" +
                "     \"timestamp\":\"2021-01-28 11:48:11\",\n" +
                "     \"raw_eeg\":[2,1,2,3,4,5,1,2],\n" +
                "     \"mediation\":10,\n" +
                "     \"attention\":80\n" +
                "   },\n" +
                "   {\n" +
                "     \"timestamp\":\"2020-12-30 13:48:11\",\n" +
                "     \"raw_eeg\":[2,1,2,3,4,5,1,2],\n" +
                "     \"mediation\":10,\n" +
                "     \"attention\":80\n" +
                "   }\n" +
                "   \n" +
                "   ]";
        JSONArray arr = new JSONArray();
        arr = JSONArray.parseArray(str);

        String str2 = "{timestamp=2021-01-28 11-48-11, raw_eeg=[2, 1, 2, 3, 4, 5, 1, 2], mediation=10, attention=80}";

        StringBuilder stringBuilder = new StringBuilder();
        String strings[] = str2.split("\\,");
        strings[0]=strings[0].substring(0,11)+"'"+strings[0].substring(11,strings[0].length())+"'";
        System.out.println(strings[0]);
        for(int i = 0;i<strings.length;i++)
        {
            if(i!=strings.length-1)
                stringBuilder.append(strings[i]+",");
            else
                stringBuilder.append(strings[i]);
        }
        System.out.println(stringBuilder);
        JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString().replace("=",":"));
        System.out.println(jsonObject.get("timestamp"));
    }

    @Test
    void testRedis(){
        redisTemplate.delete(":isRecording");
        redisTemplate.delete("Tom22:isRecording:endTime");
        System.out.println(redisTemplate.opsForValue().get("Tom22:isRecording")+";"
          +redisTemplate.opsForValue().get("Tom22:isRecording:endTime")+";"
          +redisTemplate.opsForValue().get("Tom22:isRecording:startTime")
        );
    }

    /*测试插入指定表*/
    @Test
    void testDynamicInsert(){

//        System.out.println(recordDataMapper.getRecordData("z_record_data","7597981.2020-12-20.0"));
//
//        System.out.println(recordDataMapper.getRecordData("z_record_data2","Tom22.2020-12-30.5"));
//
//        System.out.println(recordDataMapper.getRecordData("z_record_data3","Tom22.2021-01-28.19"));


        redisTemplate.opsForValue().set("test","rew");
    }

    @Test
    void testDynamic(){
     checkTimeoutScheduler.addTask("user1",10);
        checkTimeoutScheduler.addTask("user2",13);
       // CheckTimeoutScheduler checkTimeoutScheduler = new CheckTimeoutScheduler();

    }


}

