package com.tcp.thread;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.tcp.QueueSession;
import com.tcp.newStruct.JsonStruct;
import com.webSocket.WebSocketHandler;
import com.webSocket.WebSocketHandlerAgentIdSession;

public class ErgodicThread implements Runnable {
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
			logger.info("开始遍历进程");
			while (WebSocketHandler.ergodicFlag) {
				Thread.sleep(500);
				Iterator iter = queue.iterator();
//				logger.info("======================");
//				System.out.println("当前消息队列数据:"+queue.size());
//				System.out.println("对比hashcode：");
//				System.out.println("当前队列地址："+queue.hashCode());
//				System.out.println("系统初始化队列地址"+QueueSession.getHashCode());
//				if (QueueSession.getHashCode() != queue.hashCode()) {
//					throw new BaseException("消息队列出错！！！！");
//				}
//				QueueSession.getHashCode();
//				logger.info("======================");
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
					while (!queue.isEmpty()&&WebSocketHandler.ergodicFlag) {
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