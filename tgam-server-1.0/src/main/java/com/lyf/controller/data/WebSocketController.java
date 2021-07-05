package com.lyf.controller.data;

import com.lyf.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.EncodeException;
import java.io.IOException;

@Controller
public class  WebSocketController {

    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid){
        ModelAndView modelAndView = new ModelAndView("socket");
        modelAndView.addObject("cid",cid);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message){
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
            return "推送失败";
        }
        return "发送成功";
    }

    @GetMapping("/test")
    public String test(){
        return "socket";
    }

}
