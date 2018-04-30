package com.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginSession {
	private static Map<String, String> map = new ConcurrentHashMap<>();

	public static void addLoginSession(String account, String code) {
		map.put(account, code);
	}

	public static Map<String, String> getCodes() {
		return map;
	}

	public static String getCodeById(String account) {
		return map.get(account);
	}

	public static void removeChannel(String account) {
		map.remove(account);
	}
	
	public static void print(){
		System.out.println(map.toString());
	}
}
