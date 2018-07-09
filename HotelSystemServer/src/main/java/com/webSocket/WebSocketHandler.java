package com.webSocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.tcp.QueueSession;
import com.tcp.thread.ErgodicThread;

public class WebSocketHandler extends TextWebSocketHandler {
	private static final Logger logger = Logger.getLogger(WebSocketHandler.class);
	public static boolean ergodicFlag = false;// 遍历线程的标志

	// 建立连接后的回调
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		logger.info("ID:[" + sessionId + "]客户端建立连接！");
		// 判断是绑定操作还是监测操作
		String cardNum = (String) session.getAttributes().get("cardNum");
		String hotelId = (String) session.getAttributes().get("hotelId");
		if (null != cardNum) { // 绑定房间
			WebSocketHandlerAgentIdSession.put(cardNum, session);
			System.out.println("卡号id:" + cardNum);
			ergodicFlag = true;
			ErgodicThread ergodicThread = new ErgodicThread(cardNum, "PARM", "binding",QueueSession.getQueue());// 启动遍历线程
			Thread thread2 = new Thread(ergodicThread, "遍历进程");
			thread2.start();
		} else if (null != hotelId) { // 房态查询
			WebSocketHandlerAgentIdSession.put(hotelId, session);
			System.out.println("酒店id:" + hotelId);
			ergodicFlag = true;
			ErgodicThread ergodicThread = new ErgodicThread(hotelId, "hotelId", "roomStatus");// 启动遍历线程
			Thread thread2 = new Thread(ergodicThread, "遍历进程");
			thread2.start();
		} else {
			session.close();
		}
		// if (StringUtils.isNotBlank(sessionId) &&
		// StringUtils.isNotBlank(agentId)) {
		// WebSocketHandlerSession.put(sessionId, session);
		// WebSocketHandlerAgentIdSession.put(agentId, session);
		// AssociatedSession.put(sessionId, agentId);
		// session.sendMessage(new TextMessage("Establish connection success"));
		// }else {
		// logger.info("WEBSOCKETID WRONG");
		// session.close();
		// }
	}

	// 接收到数据后的回调（可以处理text，pong,binary等数据）
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// String msg = message.getPayload().toString();
		// JSONObject json = JSONObject.parseObject(msg);
		// String type = (String) json.get("type");
		// logger.info("[类型：]:" + type);
		logger.info("[接收来自ID:" + session.getId() + "客户端的消息]:" + message.getPayload());
		super.handleMessage(session, message);
	}

	// 连接关闭后的回调
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		logger.info("ID[" + session.getId() + "]客户端关闭连接");
		ergodicFlag = false;// 关闭遍历线程
		// WebSocketHandlerSession.remove(session.getId());//从sessionid
		// Handler中移除
		// WebSocketHandlerAgentIdSession.remove(AssociatedSession.get(session.getId()));//从agentId
		// Handler中移除
		// AssociatedSession.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}

	// 传输失败的回调
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("websocket传输失败");
		if (session.isOpen()) {
			session.close();
			ergodicFlag = false;
		}
		// WebSocketHandlerSession.remove(session.getId());//从sessionid
		// Handler中移除
		// WebSocketHandlerAgentIdSession.remove(AssociatedSession.get(session.getId()));//从agentId
		// Handler中移除
		// AssociatedSession.remove(session.getId());
		super.handleTransportError(session, exception);
	}

	// 发送指定的消息给客户端
	public boolean sendMsg2User(String clientId, TextMessage message) {
		return false;
	}
}
