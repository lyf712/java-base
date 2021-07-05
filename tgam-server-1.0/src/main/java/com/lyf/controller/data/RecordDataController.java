package com.lyf.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.lyf.dao.mapper.RecordMapper;
import com.lyf.sercurity.LoginToken;
import com.lyf.sercurity.SecurityParameter;
import com.lyf.service.RecordService;
import com.lyf.service.WebSocketServer;
import com.lyf.service.impl.RecordServiceImpl;
import com.lyf.utils.HandleJson;
import com.lyf.utils.TimeUtil;
import com.lyf.utils.result.Result;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.websocket.EncodeException;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @Author lyf
 * @Data 2020-12-24
 * @Descroption
 * function: 数据实时上传
 *
 * 待改进
 * queryFlag 无权限考虑
 * 数据安全控制考虑，权限考虑
 * 漏洞，每次发送的包用户名不一致？
 *
 */

@LoginToken
@Controller
@RequestMapping("record")
@EnableScheduling
public class RecordDataController {

    // 记录第几次记录数据的key
    static private String key = "";
    static public Integer timeout1 = null;
    static private Integer interval1 = null;

    // 考虑生命周期
    static private int recordIndex = 0;

    static private Timestamp firstQueryTime = null ;
    static private Timestamp lastRequestTime = null;
    static public  int flag = 0 ;//判断是否在进行存取数据

    @Autowired
    RecordService recordService;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(RecordDataController.class);

//    @LoginToken
//    @SecurityParameter
    @LoginToken
    @PostMapping(value = "/add",produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public JSONObject addData(@RequestBody JSONObject jsonObject){

        logger.info("控制层收到数据"+jsonObject);
        //响应结果
        JSONObject response = null;
        //响应数据结果
        JSONObject data = new JSONObject();

        // 分解前端数据
        String command = jsonObject.getString("commandByte"); // 命令字节
        String UUID = jsonObject.getString("UUID"); // 包的唯一标识符
        String userName = jsonObject.getString("username"); // 用户名


        JSONObject info = JSONObject.parseObject(jsonObject.getString("info").replace("=",":"));
        // info中的信息
        String dataIndex = info.getString("index");// 包序号
        String timeOut = info.getString("timeout");// 超时时长
        String interval = info.getString("interval");// 间隔
        timeout1 = Integer.valueOf(timeOut);
        interval1 = Integer.valueOf(interval);

        // 缺失处理
        if(command==null||info==null||userName==null){ //StringUtils
            response = Result.noParameter();
            return response;
        }// username应当检查是否存在


        System.out.println("控制层收到的timeout为"+timeout1);
        String dataArr = jsonObject.getString("data");// data  array

        // 查询该用户是否有未正常结束的数据
        String check = recordService.checkRecording(userName);
        System.out.println("控制层check"+userName+check);

        // 检查是否需要转发
        /*前端若需要同步，则存缓存或者设置标志位，暂时使用Redis来检查*/
        String isSync = redisTemplate.opsForValue().get(userName+":isSync");

        if("yes".equals(isSync)){

            try{
                if(!StringUtil.isNullOrEmpty(dataArr))

                WebSocketServer.sendInfo( HandleJson.handleJson(dataArr),userName);
                System.out.println("开启转发"+userName);
            }catch (IOException | EncodeException e){
                e.printStackTrace();
            }

        }

        /*
        开始记录数据:
         (1)先检查有无上传中断未正常结束的数据  <=查 key hasRecordingData,value为该未记录完的data主键
         (2)检查若未有上传中断的数据则设置该key，开始记录数据，若有则进行判断操作

        1.一旦开始便开启计时,若超时没有收到数据包,则写入已有数据(状态为上次未上传完毕)
        2.

         */
        if("000".equals(command.substring(0,3))){

            flag = 1; // 标记正在记录

          //  redisTemplate.opsForValue().set(userName+ ":" + "isRecording:status", "正在发送中");

            if(check!=null){ // 非空才是否继续上次
                // String[] infos = check.split("\\:");
                String time = redisTemplate.opsForValue().get(userName+":isRecording:endTime");
                data.put("mention","上次（"+time+"）意外中断,是否恢复") ;
                response = Result.OK(data);
                return response;
            }

            // 开始记录时间
            String startTime = TimeUtil.getAllTime();
            try{
                firstQueryTime = Timestamp.valueOf(startTime);
                // 后端查询第几次记录
                recordIndex = recordMapper.queryIndexByName(userName);
                lastRequestTime = Timestamp.valueOf(TimeUtil.getAllTime());

            }catch (Exception e){ // 解析异常？
                e.printStackTrace();
            }

            //准备开始处理数据,是否检验index？？

            this.key = userName+":"+ TimeUtil.getDate()+":"+recordIndex;
            logger.info("开始记录数据:记录key为"+key);

            // 记录缓存(第一次记录)
            boolean flag =  recordService.startRecord(key,startTime,dataIndex,dataArr);

            data.put("index",Integer.valueOf(dataIndex)); //返回当天第几次记录的第几次包,开始处理应该始终为0,即第一个包

            if(flag){
               data.put("detail","已收到并开始记录数据");
               response = Result.OK(data);
           }else {
               data.put("detail","已收到但记录数据失败");
               response = Result.ERROR(data);
           }

        } else if("001".equals(command.substring(0,3))){

//            redisTemplate.opsForValue().set(userName+ ":" + "isRecording:status", "正在发送中");

            if(check!=null){ // 非空才是否继续上次
                // String[] infos = check.split("\\:");
                String time = redisTemplate.opsForValue().get(userName+":isRecording:endTime");
                data.put("mention","上次（"+time+"）意外中断,是否恢复") ;
                response = Result.OK(data);
                return response;
            }

            flag = 1;
            // 发送
            logger.info("正在记录数据:记录key为"+key+"data为"+data);

            Timestamp now = Timestamp.valueOf(TimeUtil.getAllTime());
            lastRequestTime = now;
            data.put("index",Integer.valueOf(dataIndex)); //返回当天第几次记录的第几次包

            boolean flag = recordService.handleRecord(now,dataIndex,dataArr);//userName,recordIndex,

            if(flag){

                data.put("detail","已接收并成功处理第"+dataIndex+"个包的发送请求");
                response = Result.OK(data);
            }else {

                data.put("detail","未点击开始");
                response = Result.ERROR(data);
            }

        }else if("010".equals(command.substring(0,3))){
            // 暂停

        //    redisTemplate.opsForValue().set(userName+ ":" + "isRecording:status", "未在发送中");


            flag = 0;
            logger.info("暂停记录数据:记录key为"+key+"data为"+data);
            recordService.suspendRecord();
            data.put("detail","暂停记录数据");
            // 解析index
            data.put("last_index",Integer.valueOf(dataIndex));
            response = Result.OK(data);

        }else if("011".equals(command.substring(0,3))){

        //    redisTemplate.opsForValue().set(userName+ ":" + "isRecording:status", "正在发送中");


            // 恢复
            flag = 1;
            logger.info("恢复记录数据:记录key为"+key+"data为"+data);
            recordService.recoverRecord();
            data.put("detail","已收到恢复记录数据");
            // 解析index
            data.put("index",Integer.valueOf(dataIndex));
            response = Result.OK(data);

        }else if("100".equals(command.substring(0,3))){

//            redisTemplate.opsForValue().set(userName+ ":" + "isRecording:status", "未在发送中");

            flag=0;
            logger.info("结束记录数据:记录key为"+key+"data为"+data);
            lastRequestTime = Timestamp.valueOf(TimeUtil.getAllTime());
            String endTime = TimeUtil.getAllTime();

            boolean flag =  recordService.endRecord(userName+":"+dataIndex,endTime,dataArr);// 根据服务响应的情况来具体响应  userName,recordIndex,

            if(flag){
                data.put("detail","已成功记录当日第"+recordIndex+"次记录");
                response = Result.OK(data);
            }else {
                data.put("detail","未成功记录当日第"+recordIndex+"次记录");
                response = Result.OK(data);
            }

            /*首尾工作，结束时需要设置为不允许同步*/

            String checkSync = redisTemplate.opsForValue().get(userName+":isSync");
            if(checkSync!=null){redisTemplate.opsForValue().set(userName+":isSync","no");}
        }

        response.put("UUID",UUID);
        return response;
    }

    /**
     * 查询是否正在记录
     *
     * 开始记录、正在记录、恢复记录开启flag=1
     * 暂停记录、结束记录、超时连接 关闭flag=0
     *
     */

    @LoginToken
    @RequestMapping(value = "/queryRecordStatus",produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public JSONObject queryStatus(@RequestBody JSONObject req){

        String userName = req.getString("username");

       //响应结果
       JSONObject response;
       //响应数据结果
       JSONObject data = new JSONObject();

        Long time = 0L;

        //String rs = redisTemplate.opsForValue().get(userName+":isRecording:status");

        //System.out.println("状态查询："+rs);
        if(firstQueryTime!=null&&lastRequestTime!=null)
        {
            time  = (lastRequestTime.getTime()-firstQueryTime.getTime())/1000;//s
            data.put("time",time);
        }

       if(flag==1){//"正在发送中".equals(rs)
           data.put("status","1");
           data.put("detail","正在发送中");
       }else{
           data.put("status","0");
           data.put("detail","未在发送中");
       }

       //考虑权限问题
       response = Result.OK(data);

       return response;
    }


    /**
     * 恢复数据
     * UUID和username即可,查询redis 有无上次中断的数据(key 为username:isRecording value为中断时的相应数据的Redis的key)
     * req{
     *     UUID:""
     *     username:""
     *     commandByte:""
     * }
     *
     * @param req
     * @return
     */
    @LoginToken
    @RequestMapping("suspendLast")
    @ResponseBody
    @CrossOrigin
    public JSONObject recoverLastData(@RequestBody JSONObject req){

        JSONObject res = new JSONObject();
        JSONObject data =new JSONObject();

        String command = req.getString("commandByte");
        String userName = req.getString("username");

        // 应该判断该username是否和key中的name一致

        if("00".equals(command.substring(0,2))){ // 恢复并重新开始记录=>（1）恢复记录成功 （2）恢复失败
            recordService.handleLastData("1",userName);
            data.put("detail","已恢复上次数据");
            res = Result.OK(data);
        }else if("01".equals(command.substring(0,2))){ // 舍弃
            recordService.handleLastData("0",userName);
            data.put("detail","已清除上次数据");
            res = Result.OK(data);
        }

        return res;
    }


}
