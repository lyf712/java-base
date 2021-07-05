package com.lyf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.controller.data.RecordDataController;
import com.lyf.dao.domain.Record;
import com.lyf.dao.domain.RecordData;
import com.lyf.dao.domain.RecordInfo;
import com.lyf.dao.domain.User;
import com.lyf.dao.mapper.RecordDataMapper;
import com.lyf.dao.mapper.RecordInfoMapper;
import com.lyf.dao.mapper.RecordMapper;
import com.lyf.dao.mapper.UserMapper;
import com.lyf.service.RecordService;
import com.lyf.task.RecordDataTask;
import com.lyf.utils.TimeUtil;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DESC
 * 记录数据service层
 *
 * 一些说明：
 * 检查是否存在未存储的key设计
 * username+":isRecording"(正在记录的数据或者已结束但未保存的数据)
 * username+":isRecording:"+startTime (开始时间)
 * username+":isRecording:"+endTime (意外结束的时间=>存在未正常结束的数据)
 * 以上三个键当正常结束时都被清理，若未正常结束，将存在endTime
 *
 * 那么判断有无正在记录数据可以使用username+":isRecording"&&endTime为空去查吗？
 * 不可以因为还有暂停的控制位=>那么设计一个status位解决呢？？
 *
 */

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;
    @Autowired
    RecordInfoMapper recordInfoMapper;
    @Autowired
    RecordDataMapper recordDataMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    Logger logger = LoggerFactory.getLogger(RecordService.class);

    // 记录key,并发时设计set进行存储？？
    static private String key = "";
    // 该用并发编程
    static private HashMap keyCache = new HashMap(); //   key:userName,val:key


    private String startTime = "";
    private String endTime = "";

    @Override
    public void UnConnection(){
        String[] infos = key.split("\\:");
        logger.error(infos[0]+"数据记录意外断开连接");
        System.out.println(infos[0]+"数据记录意外断开连接" );
        redisTemplate.opsForValue().set(infos[0] + ":" + "isRecording:endTime", TimeUtil.getAllTime());
    }

    /*若查询为空说明正在记录？？*/
    @Override
    public String checkRecording(String userName) {

        String str = redisTemplate.opsForValue().get(userName + ":isRecording:endTime");
        return str;
    }

    @Override
    public boolean startRecord(String key, String startTime, String index, String data) {

        // 为空表示第一次记录
        if (this.key == "") {
            String[] infos = key.split("\\:");

            redisTemplate.opsForValue().set(infos[0] + ":" + "isRecording", key);
            redisTemplate.opsForValue().set(infos[0] + ":" + "isRecording:startTime",startTime);
            redisTemplate.opsForList().leftPush(key, data);

            redisTemplate.persist(key);
        }

        // 记录
        this.key = key;
        // String keys =this.key + ":"+index;
        // data处理,解析data; 或者前端解析jsonObject获取相应的值
        this.startTime = startTime;

        // redisTemplate.expire(this.key, Duration.ofSeconds(6000));//失效时间具体而定

        // 开启定时器
        RecordDataTask.count = 0;
        RecordDataTask.command = 1;// 开始计数
        RecordDataTask.timeOut = 60; // 暂时默认为10s

        return true;
    }

    // 存入Redis缓存中
    @Override  //String userName,String recordIndex,
    public boolean handleRecord(Timestamp now, String dataIndex, String data) {

        // 检查是否超时结束（意外中断）

      //  String endTime = redisTemplate.opsForValue().get()

        System.out.println("业务层指针处理数据..");

        // 重新计数
        RecordDataTask.count = 0;
        RecordDataTask.command = 1;
        RecordDataTask.timeOut = RecordDataController.timeout1;

        if ("".equals(this.key)) {
            return false;
        }
        // 往缓存插入
        redisTemplate.opsForList().leftPush(this.key, data); // 插入异常的反馈？？

        return true;
    }

    // 暂停
    @Override
    public boolean suspendRecord() {

        RecordDataTask.command = 1;
        RecordDataTask.count = 0;
        RecordDataTask.timeOut = 600;


        return true;
    }

    // 恢复
    @Override
    public boolean recoverRecord() {

        RecordDataTask.command = 1;// 开始计数
        RecordDataTask.count = 0;

        return true;
    }


    /*
     * 正常结束：key username:isRecording  需要清空
     *
     *
     */
    @Override ///String userName,String recordIndex,
    public boolean endRecord(String dataIndex, String endTime, String data) {

        // String key = userName+":"+TimeUtil.getDate()+":"+recordIndex+dataIndex;

        RecordDataTask.command = 0;

        String[] infos = dataIndex.split("\\:");

        redisTemplate.delete(infos[0] + ":" + "isRecording");
        // 正常结束就无此
        // redisTemplate.delete(infos[0]+":isRecording:endTime");
        redisTemplate.delete(infos[0]+":isRecording:startTime");

        System.out.println("删除" + infos[0] + ":" + "isRecording");

        if ("".equals(this.key)) {
            return false;
        }

        //"record#"+endTime+"#"+dataIndex+"#"+ 直接插入data即可？
        redisTemplate.opsForList().leftPush(this.key, data); // 插入异常的反馈？？
        this.endTime = endTime;
        Long len = redisTemplate.opsForList().size(this.key);

        List<String> list = redisTemplate.opsForList().range(this.key, 0, len);

        storageData(this.key, startTime, endTime, list);


        // 清除此次记录的缓存key

        this.key = "";//置空,防止还未开始记录就已有key
        this.endTime = "";
        this.startTime = "";
        return true;
    }



    public String storageData(String keys, String startTime, String endTime, List<String> content) {

        System.out.println("开始写入库...");
        // 写入record  user_name info_id  index timestamp
        // key username+date+recordIndex
        String[] info = keys.split("\\:");
        String userName = info[0];
        String date = info[1];
        String index = info[2];

        if (index == null || userName == null || date == null) {
            return "参数为空";
        }

        String infoId = userName + "." + date + "." + index;

        Record record = new Record();
        record.setIndex(Integer.valueOf(index));
        record.setInfoId(infoId);

        record.setTimeStamp(Timestamp.valueOf(startTime));
        record.setUserName(userName);

        recordMapper.insertRecord(record);

        // 写入record_info

        Long totalTime = (Timestamp.valueOf(endTime).getTime() - Timestamp.valueOf(startTime).getTime()) / 1000;
        String model = "驾驶";
        String device = "iPhone";

        RecordInfo recordInfo = new RecordInfo();
        recordInfo.setInfoId(infoId);
        recordInfo.setStartTime(Timestamp.valueOf(startTime));
        recordInfo.setEndTime(Timestamp.valueOf(endTime));
        recordInfo.setTotalTime(totalTime);

        System.out.println("recordInfo" + recordInfo);
        recordInfoMapper.insertRecordInfo(recordInfo);

        //遍历写入（data应为一个list）
        List<RecordData> recordDataList = new ArrayList<>();
        System.out.println("content" + content);
        logger.info("写入数据库收到的信息为:" + String.valueOf(content));
        System.out.println("写入数据为" + content);

        // 写入record_data
        // 二维数组
        // [[{},{}],[]]

        int counts = 0;

        for (int i = content.size() - 1; i >= 0; i--) {//String dataArr:content

            String dataArr = content.get(i);
            System.out.println("dataArray:"+dataArr);

            JSONArray datas = JSONArray.parseArray(handleJson(dataArr));


            for (int k = datas.size() - 1; k >= 0; k--) {

                JSONObject recordData = JSONObject.parseObject(datas.get(k).toString());
                String timestamp = recordData.getString("timestamp");
                // timestamp格式处理
                try {
                    timestamp = timestamp.substring(0, 10) + " " + timestamp.substring(11, timestamp.length()).replace("-", ":");
                }catch (Exception e){

                }

                String mediation = recordData.getString("mediation");
                String attention = recordData.getString("attention");
                String dataArr2 = recordData.getString("raw_eeg");

                JSONArray arr = JSONArray.parseArray(dataArr2);
                RecordData recordData1 = new RecordData();

                recordData1.setInfoId(infoId);
                recordData1.setAttention(Float.valueOf(attention));
                recordData1.setMediation(Float.valueOf(mediation));
                recordData1.setChannel1(arr.toArray()[0].equals("") ? null : Float.valueOf(arr.toArray()[0]+"") );
                recordData1.setChannel2(arr.toArray()[1].equals("") ? null : Float.valueOf(arr.toArray()[1]+"") );
                recordData1.setChannel3(arr.toArray()[2].equals("") ? null : Float.valueOf(arr.toArray()[2]+"") );
                recordData1.setChannel4(arr.toArray()[3].equals("") ? null : Float.valueOf(arr.toArray()[3]+"") );
                recordData1.setChannel5(arr.toArray()[4].equals("") ? null : Float.valueOf(arr.toArray()[4]+"") );
                recordData1.setChannel6(arr.toArray()[5].equals("") ? null : Float.valueOf(arr.toArray()[5]+"") );
                recordData1.setChannel7(arr.toArray()[6].equals("") ? null : Float.valueOf(arr.toArray()[6]+"") );
                recordData1.setChannel8(arr.toArray()[7].equals("") ? null : Float.valueOf(arr.toArray()[7]+"") );
                recordData1.setDataIndex(String.valueOf(counts));
                recordData1.setTimestamp(Timestamp.valueOf(timestamp));

                recordDataList.add(recordData1);
                System.out.println("recordDataTest:" + recordData1);
            }
            counts++;
        }
            redisTemplate.delete(this.key);

            this.key = "";//置空,防止还未开始记录就已有key
            this.endTime = "";
            this.startTime = "";

            System.out.println("recordList:" + recordDataList);

            if(recordDataList!=null){
                try{

                    User user = userMapper.queryByUserName(userName);
                    recordDataMapper.insertBatchRecordData("z_record_data_"+user.getId(),recordDataList);

                }catch (MyBatisSystemException e){
                    e.printStackTrace();
                    return "插入异常";
                }
            }
            return "记录成功";
        }

        public String handleLastData(String command,String userName){

            String recordKey = redisTemplate.opsForValue().get(userName+":isRecording");
            System.out.println("recordKey:"+recordKey);

        if("1".equals(command)){

            if(recordKey==null){return "恢复数据为空";}
            Long len = redisTemplate.opsForList().size(recordKey);
            List<String> list = redisTemplate.opsForList().range(recordKey, 0, len);
            String startTime = redisTemplate.opsForValue().get(userName+":isRecording"+":startTime");
            String endTime = redisTemplate.opsForValue().get(userName+":isRecording"+":endTime");

            System.out.println("上次中断数据为:"+list);

            storageData(recordKey,startTime,endTime,list);
            if(redisTemplate.opsForValue().get(userName+":isRecording:endTime")!=null){ redisTemplate.delete(userName+":isRecording:endTime");}
            if(redisTemplate.opsForValue().get(userName+":isRecording:startTime")!=null){ redisTemplate.delete(userName+":isRecording:startTime");}
            if(recordKey!=null&&redisTemplate.opsForList().size(recordKey)!=0){ redisTemplate.delete(recordKey);}
            if(redisTemplate.opsForValue().get(userName+":isRecording")!=null){ redisTemplate.delete(userName+":isRecording");}

        }else if("0".equals(command)){
            System.out.println("清除key:"+userName+":isRecording:endTime");
            if(redisTemplate.opsForValue().get(userName+":isRecording:endTime")!=null){ redisTemplate.delete(userName+":isRecording:endTime");}
            if(redisTemplate.opsForValue().get(userName+":isRecording:startTime")!=null){ redisTemplate.delete(userName+":isRecording:startTime");}
            if(recordKey!=null&&redisTemplate.opsForList().size(recordKey)!=0){ redisTemplate.delete(recordKey);}
            if(redisTemplate.opsForValue().get(userName+":isRecording")!=null){ redisTemplate.delete(userName+":isRecording");}

        }
        return "已处理";
    }

        // 处理timestamp单引号问题
      static   String handleJson(String dataArr){
           StringBuilder stringBuilder = new StringBuilder();
           for(int i =0;i<dataArr.length();i++){
               if(dataArr.charAt(i)!='p'){stringBuilder.append(dataArr.charAt(i));}
               else {
                    stringBuilder.append("p="+"'"+dataArr.substring(i+2,i+21)+"'");
                    i+=20;
               }
           }
            System.out.println(stringBuilder);
           return stringBuilder.toString().replace("=",":");
        }
}


