package com.webSocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSONObject;
import com.tcp.jsonMsg.JsonMsg;
import com.tcp.netty.ClientHandler;
import com.test.TestQueue;

public class WebSocketHandler extends TextWebSocketHandler {
	private static final Logger logger = Logger.getLogger(WebSocketHandler.class);
	protected static boolean ergodicFlag = false;//遍历线程的标志

	// 建立连接后的回调
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		logger.info("ID:[" + sessionId + "]客户端建立连接！");
		// 判断是绑定操作还是监测操作
		String cardNum = (String) session.getAttributes().get("cardNum");
		String agentId = (String) session.getAttributes().get("agentId");
		if (null != cardNum) {
			WebSocketHandlerAgentIdSession.put(cardNum, session);
			System.out.println("卡号id:" + cardNum);
			System.out.println(ClientHandler.queueu.toString());// 打印消息队列
			ErgodicThread ergodicThread = new ErgodicThread(cardNum);//启动遍历线程
			Thread thread2 = new Thread(ergodicThread, "遍历进程");
			thread2.start();
			ergodicFlag = true;
		} else if (null != agentId) {
			System.out.println("中继id:" + agentId);
		}else {
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
		logger.info("[接收来自ID:" + session.getId() + "客户端的消息]:" + message.getPayload());
		super.handleMessage(session, message);
	}

	// 连接关闭后的回调
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("ID[" + session.getId() + "]客户端关闭连接");
		ergodicFlag = false;//关闭遍历线程
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
		logger.info("传输失败");
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

class ErgodicThread implements Runnable {

	@Override
	public void run() {
		long time = new Date().getTime();
		int count = 0;
		try {
			while (WebSocketHandler.ergodicFlag) {
				ConcurrentLinkedQueue<JsonMsg> queue = ClientHandler.queueu.getStorage();
				Iterator iter = queue.iterator();
				System.out.println("-----------开始遍历-----------");
				while (iter.hasNext()) {
					count++;
					JsonMsg jsonMsg = (JsonMsg) iter.next();
					JSONObject jsonObject = jsonMsg.getJsonObject();
					String jsonContent = jsonObject.toJSONString();
					if (cardNum.equals(jsonObject.getString("卡号"))) {
						System.out.println("##找到一个叛徒:" + jsonContent);
						WebSocketSession session = WebSocketHandlerAgentIdSession.get(cardNum);
						ClientHandler.queueu.getStorage().remove(jsonMsg);
						session.sendMessage(new TextMessage(jsonContent));
						continue;
					}
					System.out.println(jsonContent);
				}
				System.out.println("--遍历结束，一共遍历个数：" + ClientHandler.queueu.getStorage().size() + "休息两秒--");
				// System.out.println("当前时间" + (new Date().getTime() - time));
				Thread.sleep(3000);
				// if (new Date().getTime() - time > 10000) {
				// System.out.println("✘✘✘✘✘✘✘✘用户停止了监听");
				// TestQueue.msgQueue = new MsgQueue<JsonMsg>();
				// TestQueue.msgQueue.print();
				// flag = false;
				// }
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String cardNum;

	public ErgodicThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErgodicThread(String cardNum) {
		super();
		this.cardNum = cardNum;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

}