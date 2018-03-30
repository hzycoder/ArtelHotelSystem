package com.webSocket;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 握手拦截器
 * @author huangzhenyang 
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	private static final Logger logger = Logger.getLogger(WebSocketHandshakeInterceptor.class);

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		logger.info("beforeHandshke");
		String agentId = ((ServletServerHttpRequest)request).getServletRequest().getParameter("agentId");
		logger.info("get agentId from client:"+agentId);
		if (StringUtils.isNotBlank(agentId)) {
			attributes.put("agentId", agentId);
			return true;
		}else {
			logger.info("websocket's parameter is wrong");
			return false;
		}
	}

}
