package com.webSocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseException;
import com.hotel.controller.HotelController;
import com.tcp.QueueSession;
import com.tcp.newStruct.JsonStruct;

public class WebSocketHandler extends TextWebSocketHandler {
	private static final Logger logger = Logger.getLogger(WebSocketHandler.class);
	protected static boolean ergodicFlag = false;// 遍历线程的标志

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
		logger.info("ID[" + session.getId() + "]客户端关闭连接");
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
	String key;
	String name;
	String type;
	ConcurrentLinkedQueue<JsonStruct> queue;
	private static final Logger logger = Logger.getLogger(ErgodicThread.class);
	
	@Override
	public void run() {
		System.out.println("key:" + key + "name:" + name + " type:" + type);
		long time = new Date().getTime();
		int count = 0;
		try {
			while (WebSocketHandler.ergodicFlag) {
				Thread.sleep(500);
				Iterator iter = queue.iterator();
				System.out.println("======================");
				logger.info("======================");
				logger.info("遍历进程");
//				System.out.println("当前消息队列数据:"+queue.size());
//				System.out.println("对比hashcode：");
//				System.out.println("当前队列地址："+queue.hashCode());
//				System.out.println("系统初始化队列地址"+QueueSession.getHashCode());
//				if (QueueSession.getHashCode() != queue.hashCode()) {
//					throw new BaseException("消息队列出错！！！！");
//				}
//				QueueSession.getHashCode();
				logger.info("======================");
				switch (type) {
				case "binding":
//					while (iter.hasNext()) {
					while (!queue.isEmpty()&&WebSocketHandler.ergodicFlag) {
						logger.info("遍历消息，卡号："+key);
						Thread.sleep(1000);
						for(JsonStruct jsonStruct : queue){
//							count++;
//							JsonStruct jsonStruct = (JsonStruct) iter.next();
							JSONObject jsonObject = jsonStruct.getContent();
							String jsonContent = jsonObject.toJSONString();
							if (key.equals(jsonObject.getString("PARM"))) {
								// System.out.println("##找到一个叛徒:" + jsonContent);
								WebSocketSession session = WebSocketHandlerAgentIdSession.get(key);
								QueueSession.getQueue().remove(jsonStruct);
								synchronized(session){
									if (session.isOpen()) {
										session.sendMessage(new TextMessage(jsonContent));
									}
								}
								continue;
							}
						}
					}
					break;
				case "roomStatus":
					while (iter.hasNext()) {
						Thread.sleep(500);
						count++;
						JsonStruct jsonStruct = (JsonStruct) iter.next();
						JSONObject jsonObject = jsonStruct.getContent();
						String jsonContent = jsonObject.toJSONString();
						logger.info("遍历房态消息");
						if (key.equals(jsonObject.getString(name))) {
							// System.out.println("##找到一个叛徒:" + jsonContent);
							WebSocketSession session = WebSocketHandlerAgentIdSession.get(key);
							QueueSession.getQueue().remove(jsonStruct);
							session.sendMessage(new TextMessage(jsonContent));
							continue;
						}
					}
					break;
				default:
					break;
				}
				// System.out.println("--遍历结束，一共遍历个数：" +
				// ClientHandler.queueu.getStorage().size() + "休息3秒--");
				// System.out.println("当前时间" + (new Date().getTime() - time));
				// if (new Date().getTime() - time > 10000) {
				// System.out.println("✘✘✘✘✘✘✘✘用户停止了监听");
				// TestQueue.msgQueue = new MsgQueue<JsonMsg>();
				// TestQueue.msgQueue.print();
				// flag = false;
				// }
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ErgodicThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErgodicThread(String key, String name, String type) {
		super();
		this.key = key;
		this.name = name;
		this.type = type;
	}

	public ErgodicThread(String key, String name, String type, ConcurrentLinkedQueue<JsonStruct> queue) {
		super();
		this.key = key;
		this.name = name;
		this.type = type;
		this.queue = queue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}