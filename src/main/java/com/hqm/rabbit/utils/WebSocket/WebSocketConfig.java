package com.hqm.rabbit.utils.WebSocket;

import com.hqm.rabbit.common.WebSocketServer;
import com.hqm.rabbit.mapper.ChatMapper;

import com.hqm.rabbit.service.UserService;
import com.hqm.rabbit.utils.security.JwtUtils;
import org.apache.ibatis.javassist.tools.web.Webserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setSenderService(JwtUtils jwtUtils, UserService UserService, ChatMapper chatmapper){
        WebSocketServer.jwtutils = jwtUtils;
        WebSocketServer.userservice=UserService;
        WebSocketServer.chatmapper = chatmapper;
    }



}
