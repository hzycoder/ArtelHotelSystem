package com.test;

import com.tcp.netty.ClientBootstrap;

public class Test {
	public static void main(String[] args) {
		Test.nettyTest();
	}

	public static void nettyTest() {
		ClientBootstrap.connectServer("192.168.0.110", 9909);
	}

}
