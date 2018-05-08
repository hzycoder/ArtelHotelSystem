package com.common.load;

import javax.annotation.PostConstruct;

import com.tcp.netty.ClientBootstrap;

public class LoadManager {
	
	@PostConstruct
	public void loadInit() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ClientBootstrap.connectServer("192.168.0.110", 7777);
			}
		}).start();
	}

}
