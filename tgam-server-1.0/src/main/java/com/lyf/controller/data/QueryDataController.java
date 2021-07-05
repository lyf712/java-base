package com.lyf.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.lyf.controller.vomain.DataInfoVO;
import com.lyf.controller.vomain.DataVo;
import com.lyf.sercurity.LoginToken;
import com.lyf.service.QueryRecordService;
import com.lyf.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/***
 * @Author 李云飞
 * @Date 2020-12-29
 * @Version tgam 1.0
 * function:查询数据
 *
 */

@RestController
@RequestMapping("record")
public class QueryDataController {

    @Autowired
    QueryRecordService queryRecordService;
    @Autowired
    StringRedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(QueryDataController.class);


    @LoginToken
    @RequestMapping(value = "/queryData",produces = "application/json")
    @CrossOrigin
    public JSONObject queryData(@RequestBody JSONObject req){

        /*声明响应JSONObject*/
        JSONObject resp;
        JSONObject data = new JSONObject();
        List<JSONObject> info = new ArrayList<>();
        List<JSONObject> detailData = new ArrayList<>();

        String userName = req.getString("username");
        String date = req.getString("date");
        /*拼接查询info*/
        String dataInfo = userName+"."+date;

        /*日志记录*/
        logger.info(userName+"查询"+date+"数据");

        /*查询数据信息和数据*/
        List<DataInfoVO> dataInfoVoList = queryRecordService.getDataInfo(dataInfo);
        List<DataVo> dataVoList = queryRecordService.getRecordData(dataInfo);


        for(DataInfoVO dataInfoVO :dataInfoVoList){
            JSONObject tempDataInfo = new JSONObject();
            tempDataInfo.put("beginDate",dataInfoVO.getStartTime().toString().substring(10,18));//beginTime更好？
            tempDataInfo.put("endDate",dataInfoVO.getEndTime().toString().substring(10,18));
            tempDataInfo.put("pattern","驾驶");
            info.add(tempDataInfo);
        }

        for(DataVo dataVo:dataVoList){
            JSONObject detailData1 = new JSONObject();
            detailData1.put("attention",dataVo.getAttention().toString());
            detailData1.put("meditation",dataVo.getMeditation().toString());
            detailData1.put("timestamp",dataVo.getTimestamp().toString());
            detailData1.put("raw_eeg",dataVo.getRawEeg());
            detailData.add(detailData1);
        }

        data.put("info",info);
        data.put("detailData",detailData);
        resp = Result.OK(data);
        return resp;
    }



    @LoginToken
    @RequestMapping(value = "/queryDataInfo",produces = "application/json")
    @CrossOrigin
    public JSONObject queryDataInfo(@RequestBody JSONObject jsonObject){

        JSONObject jsonObject1 = new JSONObject();
        String userName = jsonObject.getString("username");
        String date = jsonObject.getString("date");
        String dataInfo = userName+"."+date;
        List<DataInfoVO> list = queryRecordService.getDataInfo(dataInfo);

        List<Map> list1 = new ArrayList<>();
        for(DataInfoVO dataInfoVO :list){
            Map<String,String> detailInfo = new HashMap<>();
            detailInfo.put("beginDate",dataInfoVO.getStartTime().toString().substring(0,16));//beginTime更好？
            detailInfo.put("endDate",dataInfoVO.getEndTime().toString().substring(0,16));
            detailInfo.put("pattern","驾驶");
            list1.add(detailInfo);
        }
        jsonObject1.put("info",list1);
        return jsonObject1;
    }


    @LoginToken
    @PostMapping("/isSync")
    @CrossOrigin
    JSONObject allowSync(@RequestBody JSONObject req){

        JSONObject res;
        JSONObject data = new JSONObject();

        String isSync = req.getString("isSync");
        String userName = req.getString("userName");
        if("yes".equals(isSync)){
            redisTemplate.opsForValue().set(userName+":isSync","yes");
            data.put("detail","设置同步查看");
            res = Result.OK(data);
        }else {
            redisTemplate.opsForValue().set(userName+":isSync","no");
            data.put("detail","未设置同步查看");
            res = Result.OK(data);
        }

        return res;
    }



}
