package com.kh.elephant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.*;

@Controller
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커를 "/topic"으로 설정합니다.
        config.enableSimpleBroker("/topic");
        // 클라이언트로부터 메시지를 "/app"으로 라우팅합니다.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결을 "/chat" 엔드포인트로 설정합니다.
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
    }
}