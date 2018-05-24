package com.tcp.services;

import io.netty.channel.ChannelFuture;

public interface TcpService {
	public void upgrade(String hotelId);
	public boolean roomOperation(String json);
}
