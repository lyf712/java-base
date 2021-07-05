package com.lyf.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * WS协议的controller相当于
 * 参考博文：https://blog.csdn.net/moshowgame/article/details/80275084
 *
 * userId <==> username
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

    static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) { // 注解回忆: @PathParam 获取路径上的参数（对应上面）

        this.session = session;
        this.userId=userId;

        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        logger.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException | EncodeException e) {
            logger.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        logger.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    /*通过websocket传输数据,

      (1)建立连接   <=> 开始传输数据
      (2)交互      <=> 发送数据包
      (3)断开连接   <=>

    *
    *
    * */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("用户消息:"+userId+",报文:"+message);

        //可以群发消息
        //消息保存到数据库、redis


        System.out.println("用户信息"+userId+message);
        // 调用相应服务即可？
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSONObject.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId",this.userId);
                String toUserId=jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else{
                    logger.error("请求的userId:"+toUserId+"不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }


    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException, EncodeException {
      this.session.getBasicRemote().sendText(message);

      // this.session.getBasicRemote().sendObject(JSONArray.parse(message));
    }

    /*
    推送JSONObject，无需直接在下一个函数一起发送即可
     */
//    public void sendData(JSONObject res) throws IOException, EncodeException {
//        this.session.getBasicRemote().sendObject(res);
//    }

    /**
     * 自定义userId,在前端(APP 另外一个客户）controller 发送数据（）处调用
     * 调用的前提：
     * （1）APP正在发送数据
     * （2）web端确定建立连接
     *
     * （3）如何转发推送？？
     * a.APP正在调用的接口处调用此方法（对应示例中的controller推送新消息）
     * b.但是controller的调用条件应该为前端确定同步数据展示，所以此 controller应该设立一个阀门控制
     *
     * @param userId
     */
    public static void sendRecordData(JSONObject transportData,String userId) throws IOException, EncodeException { //转发数据
        /*data处理可以单独写一个函数*/
        // 用户不只一个，在webSocketMap中获取userId，该用户的session，将其发送给他
        /*需要判断是否在线吗？ 不需要？因为controller中已经进行判断保证前端打开socket以及userId合法*/
        webSocketMap.get(userId).session.getBasicRemote().sendObject(transportData);

    }




    /**
     * 发送自定义消息（后端推送到前端）
     * */

    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException, EncodeException {

        logger.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            logger.error("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
