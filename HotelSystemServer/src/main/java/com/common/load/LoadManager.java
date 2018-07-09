package com.common.load;

import javax.annotation.PostConstruct;

import com.tcp.QueueSession;
import com.tcp.netty.ClientBootstrap;

public class LoadManager {

	@PostConstruct
	public void loadInit() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ClientBootstrap.connectServer("192.168.0.110", 7777);
//				ClientBootstrap.connectServer("47.106.99.219", 8007);
//				System.out.println("初始化时，消息队列的地址：" + QueueSession.getQueue().hashCode());
//				QueueSession.setHashCode(QueueSession.getQueue().hashCode());
				// ClientBootstrap.connectServer("localhost", 7777);
			}
		}).start();
	}

}
