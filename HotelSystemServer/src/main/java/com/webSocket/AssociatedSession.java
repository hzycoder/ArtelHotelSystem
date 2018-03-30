package com.webSocket;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AssociatedSession {
	public static ConcurrentMap<String, String> associatedSessionMap = new ConcurrentHashMap<>();

	public static void put(String sessionId, String agentId) {
		associatedSessionMap.put(sessionId, agentId);
	}

	public static String get(String sessionId) {
		return associatedSessionMap.get(sessionId);
	}
	public static String getSeesionId(String agentId){
		Set<String> keySet = associatedSessionMap.keySet();
		System.out.println("keySet:"+keySet);
		for (String key : keySet) {
			System.out.println(associatedSessionMap.get(key).toString());
			if (associatedSessionMap.get(key).equals(agentId)) {
				return key;
			}
		}
		return null;
	}

	public static void remove(String sessionId) {
		associatedSessionMap.remove(sessionId);
	}

	public static Collection<String> getValues() {
		return associatedSessionMap.values();
	}

	public static void print() {
		System.out.println(associatedSessionMap.toString());
	}

	public static boolean containKey(String sessionId) {
		return associatedSessionMap.containsKey(sessionId);
	}
}
