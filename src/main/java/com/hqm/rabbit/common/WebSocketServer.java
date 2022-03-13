package com.hqm.rabbit.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hqm.rabbit.domain.entity.SysChat;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.mapper.ChatMapper;
import com.hqm.rabbit.mapper.LoginMapper;
import com.hqm.rabbit.service.UserService;
import com.hqm.rabbit.utils.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.hqm.rabbit.mapper.LoginMapper;


/**
 * @author websocket服务
 */

@ServerEndpoint(value = "/imserver/{username}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    @Autowired
    public static JwtUtils jwtutils;

    @Autowired
    public static UserService userservice;

    @Autowired
    public static ChatMapper chatmapper;
    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        HashMap<String, List<SysUserVo>> stringListHashMap = new HashMap<>();
        List<SysUserVo> sysUserVosList = userservice.selectUserVo();
        List<SysUserVo> userVoonlineList = jwtutils.getUserVoonlineList();
        for(SysUserVo SysUserVo :sysUserVosList){
            Boolean isbiaoshi=false;
            for(SysUserVo SysUseronLineVo :userVoonlineList){
                if(SysUserVo.getUsername().equals(SysUseronLineVo.getUsername())){
                    isbiaoshi=true;
                }
            }
            if(!isbiaoshi){
                userVoonlineList.add(SysUserVo);
            }
        }

        stringListHashMap.put("usernamelist",userVoonlineList);
        String str = JSON.toJSONString(stringListHashMap); // List转json
        sessionMap.put(username, session);
        log.info("有新用户加入，username={}, 当前在线人数为：{}", username, userVoonlineList.size());
        log.info("所有用户{}", stringListHashMap);
//        JSONObject result = new JSONObject();
//        JSONArray array = new JSONArray();
//        result.put("users", array);
////        result.put("state", array);
////        result.put("users", array);
//        for (Object key : sessionMap.keySet()) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("username", key);
//            // {"username", "zhang", "username": "admin"}
//            array.add(jsonObject);
//        }
    ////        {"users": [{"username": "zhang"},{ "username": "admin"}]}
        sendAllMessage(str);  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
//        JSONObject obj = JSONUtil.parseObj(message);
        JSONObject obj = JSONObject.parseObject(message);
        String toUsername = obj.getString("to"); // to表示发送给哪个用户，比如 admin
        String text = obj.getString("text"); // 发送的消息文本  hello
        // {"to": "admin", "text": "聊天文本"}
        Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
        SysChat sysChat = new SysChat();
        sysChat.setCto(toUsername);
        sysChat.setCfrom(username);
        sysChat.setCtext(text);
        chatmapper.addChat(sysChat);
        if (toSession != null) {
            // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
            // {"from": "zhang", "text": "hello"}
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("to", toUsername);  // from 是 zhang
            jsonObject.put("from", username);  // from 是 zhang
            jsonObject.put("text", text);  // text 同上面的text
            log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
            this.sendMessage(jsonObject.toString(), toSession);

        } else {
            log.info("发送失败，未找到用户username={}的session", toUsername);
        }


    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给所以客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

}
