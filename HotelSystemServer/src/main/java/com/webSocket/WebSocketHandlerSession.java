package com.webSocket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author huangzhenyang 根据sessionId标识的websocketHanlder
 */
public class WebSocketHandlerSession {
	public static ConcurrentMap<String, WebSocketSession> WebSocketHandlerSessionMap = new ConcurrentHashMap<>();

	public static void put(String sessionId, WebSocketSession session) {
		WebSocketHandlerSessionMap.put(sessionId, session);
	}

	public static WebSocketSession get(String sessionId) {
		return WebSocketHandlerSessionMap.get(sessionId);
	}

	public static void remove(String sessionId) {
		WebSocketHandlerSessionMap.remove(sessionId);
	}

	public static Collection<WebSocketSession> getValues() {
		return WebSocketHandlerSessionMap.values();
	}

	public static void print() {
		System.out.println(WebSocketHandlerSessionMap.toString());
	}

	public static boolean containKey(String sessionId) {
		return WebSocketHandlerSessionMap.containsKey(sessionId);
	}
}
