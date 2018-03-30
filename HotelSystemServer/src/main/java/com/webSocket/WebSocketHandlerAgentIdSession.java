package com.webSocket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author huangzhenyang
 * 根据agentId标识的websocketHanlder
 */
public class WebSocketHandlerAgentIdSession {

	public static ConcurrentMap<String, WebSocketSession> WebSocketHandlerAgentIdSessionMap = new ConcurrentHashMap<>();

	public static void put(String agentId, WebSocketSession session) {
		WebSocketHandlerAgentIdSessionMap.put(agentId, session);
	}

	public static WebSocketSession get(String agentId) {
		return WebSocketHandlerAgentIdSessionMap.get(agentId);
	}

	public static void remove(String agentId) {
		WebSocketHandlerAgentIdSessionMap.remove(agentId);
	}

	public static Collection<WebSocketSession> getValues() {
		return WebSocketHandlerAgentIdSessionMap.values();
	}

	public static void print() {
		System.out.println(WebSocketHandlerAgentIdSessionMap.toString());
	}

	public static boolean containKey(String agentId) {
		return WebSocketHandlerAgentIdSessionMap.containsKey(agentId);
	}
}
