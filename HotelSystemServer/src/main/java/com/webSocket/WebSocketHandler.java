package com.webSocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
	private static final Logger logger = Logger.getLogger(WebSocketHandler.class);
	// 建立连接后的回调
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userId = session.getId();
		logger.info("ID:[" +userId +"]客户端建立连接！");
		if (userId != null) {
			WebSocketHandlerSession.put(userId, this);
			session.sendMessage(new TextMessage("成功建立连接"));
		}
	}

	// 接收到数据后的回调（可以处理text，pong,binary等数据）
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info("[接收来自ID:"+session.getId()+"客户端的消息]:" + message.getPayload());
		String receiveMsg = (String) message.getPayload();
		StringBuffer id = null;
		String userId = session.getId();
		String agentId = null;
		if (receiveMsg.indexOf("agentId#@#")!=-1) {
			agentId = receiveMsg.split("#@#")[1];
		}
		id.append(userId).append(":").append(agentId);
		if (WebSocketHandlerSession.containKey(id.toString())) {
			
		}
		super.handleMessage(session, message);
	}

	// 连接关闭后的回调
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("ID["+session.getId()+"]客户端关闭连接");
		WebSocketHandlerSession.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}

	// 传输失败的回调
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("传输失败");
		if (session.isOpen()) {
			session.close();
		}
		WebSocketHandlerSession.remove(session.getId());
		super.handleTransportError(session, exception);
	}

	// 发送指定的消息给客户端
	public boolean sendMsg2User(String clientId, TextMessage message) {
		return false;
	}
}