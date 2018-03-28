package com.tcp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class ChannelSession {
	private static Map<String, Channel> map = new ConcurrentHashMap<>();

	public static void addChannelSession(String id, Channel channel) {
		map.put(id, channel);
	}

	public static Map<String, Channel> getChannels() {
		return map;
	}

	public static Channel getChannelById(String id) {
		return map.get(id);
	}

	public static void removeChannel(String id) {
		map.remove(id);
	}
}
