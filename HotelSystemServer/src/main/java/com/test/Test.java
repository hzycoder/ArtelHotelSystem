package com.test;

import org.apache.commons.lang3.StringUtils;

import com.tcp.netty.ClientBootstrap;
import com.webSocket.WebSocketHandler;
import com.webSocket.WebSocketHandlerSession;

public class Test {
	public static void main(String[] args) {

		// byte byte_max = Byte.MAX_VALUE;
		// byte b = 0x;
		// String s = "03bc 当";
		// byte[] b = (byte)0xaa,(byte)0xaa,(byte)0xaa;
		// System.out.println(b);
		// ClientBootstrap.connectServer("192.168.0.110", 8808);
		// String a = "agent#@#123#@#";
		// System.out.println(a.split("#@#")[1]);

		// WebSocketHandlerSession.put("1", new WebSocketHandler());
		// WebSocketHandlerSession.print();
		// WebSocketHandlerSession.put("2", new WebSocketHandler());
		// WebSocketHandlerSession.print();
		// WebSocketHandlerSession.put("3", new WebSocketHandler());
		// WebSocketHandlerSession.print();
		// if (!WebSocketHandlerSession.containKey("1")) {
		// WebSocketHandlerSession.put("1", new WebSocketHandler());
		// WebSocketHandlerSession.print();
		// }else {
		// System.out.println("yes");
		// }
		// String a = "agentId#@#";
		// String b = "sdfljweofagetId#@#lfjweojf";
		// System.out.println(b.indexOf(a));

		//
		String sessionId = "0";
		String agentId = "9999";
		if (StringUtils.isNotBlank(sessionId) && StringUtils.isNotBlank(agentId)) {
			String id = appendID(sessionId, agentId);
			System.out.println("测试id:" + id);
//			session.sendMessage(new TextMessage("成功建立连接"));
		} else {
			System.out.println("websocketId WRONG");
//			session.close();
		}

	}

	public static String appendID(String sessionId, String agentId) {
		StringBuffer id = new StringBuffer();
		id.append(sessionId).append(":").append(agentId);
		return id.toString();
	}
}
