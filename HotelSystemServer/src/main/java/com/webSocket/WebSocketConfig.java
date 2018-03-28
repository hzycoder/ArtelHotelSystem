package com.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
//@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 允许连接的域,只能以http或https开头
//		String[] allowsOrigins = { "http://www.xxx.com" };
		// WebIM WebSocket通道
		registry.addHandler(chatWebSocketHandler(), "/websocket").setAllowedOrigins("*");
		registry.addHandler(chatWebSocketHandler(), "/sockjs/websocket").setAllowedOrigins("*")
				.addInterceptors(myInterceptor()).withSockJS();
	}

	@Bean
	public WebSocketHandler chatWebSocketHandler() {
		return new WebSocketHandler();
	}

	@Bean
	public WebSocketHandshakeInterceptor myInterceptor() {
		return new WebSocketHandshakeInterceptor();
	}
}