package com.tcp.netty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

public class ConnectionListener implements ChannelFutureListener {
	private ClientBootstrap clientBootstrap;
	private static int reConnectCount;

	public ConnectionListener(ClientBootstrap clientBootstrap) {
		this.clientBootstrap = clientBootstrap;
	}

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		if (!future.isSuccess()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("第" + (++reConnectCount) + "次尝试重连-----"+sdf.format(new Date().getTime()));
			final EventLoop loop = future.channel().eventLoop();
			loop.schedule(new Runnable() {

				@Override
				public void run() {
					clientBootstrap.connect("192.168.0.110", 7777);
				}
			}, reConnectCount << 2, TimeUnit.SECONDS);
		} else {
			reConnectCount = 0;
			System.out.println("reConnect success");
		}
	}

}
