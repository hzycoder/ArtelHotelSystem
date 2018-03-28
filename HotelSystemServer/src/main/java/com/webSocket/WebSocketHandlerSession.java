package com.webSocket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;


public class WebSocketHandlerSession {
	private static final Logger logger = Logger.getLogger(WebSocketHandlerSession.class);
	public static ConcurrentMap<String, WebSocketHandler> WebSocketHandlerSessionMap = new ConcurrentHashMap<>();

	public static void put(String id, WebSocketHandler webSocketHandler) {
		WebSocketHandlerSessionMap.put(id, webSocketHandler);
	}

	public static WebSocketHandler get(String id) {
		return WebSocketHandlerSessionMap.get(id);
	}

	public static void remove(String id) {
		WebSocketHandlerSessionMap.remove(id);
	}

	public static Collection<WebSocketHandler> getValues() {
		return WebSocketHandlerSessionMap.values();
	}
	
	public static void print(){
//		logger.info(WebSocketHandlerSessionMap.toString());
		System.out.println(WebSocketHandlerSessionMap.toString());
	}
	public static boolean containKey(String key){
		return WebSocketHandlerSessionMap.containsKey(key);
	}
}
