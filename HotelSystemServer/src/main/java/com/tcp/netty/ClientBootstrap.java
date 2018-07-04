package com.tcp.netty;

import java.util.concurrent.TimeUnit;

import com.tcp.frameStruct.SyncFrameCoder;
import com.tcp.frameStruct.SyncFrameDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class ClientBootstrap {
	public void connect(String ip, int port) {
		// 创建客户端NIO线程组
		NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		// 创建客户端Bootstrap引导程序
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup);
		bootstrap.channel(NioSocketChannel.class);

		bootstrap.remoteAddress(ip, port);
		// bootstrap.option(option, value)
		// 初始化channel
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				// 添加消息编解码
				ChannelPipeline pipeline = socketChannel.pipeline();
				//自定义json编解码
				pipeline.addLast(new SyncFrameDecoder());
				pipeline.addLast(new SyncFrameCoder());
//				pipeline.addLast(new JsonEncoder());
//				pipeline.addLast(new JsonDecoder());
				pipeline.addLast(new IdleStateHandler(0,10,0, TimeUnit.SECONDS));
				// 添加handler
				pipeline.addLast(new ClientHandler());
			}

		});

		// 让客户端和服务端连接
		ChannelFuture channelFuture = bootstrap.connect(ip, port).addListener(new ConnectionListener(this));
//		channelFuture.addListener(new ChannelFutureListener() {
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				if (future.isSuccess()) {
//					System.out.println("connect Server susccess");
//					reConnectCount = 0;
//				} else {
//					System.out.println("connected Server failure");
//					future.channel().eventLoop().shutdownGracefully();
//					System.out.println("reConnect");
//					final EventLoop loop = future.channel().eventLoop();
//					loop.schedule(new Runnable() {
//						@Override
//						public void run() {
//							System.out.println("run method");
//							connect("192.168.0.110", 7777);
//						}
//					}, 1L, TimeUnit.SECONDS);
//					Thread.sleep(80);
//				}
//
//			}
//		});

	}

	public static void connectServer(String ip, int port) {
		System.out.println("开始尝试连接");
		new ClientBootstrap().connect(ip, port);
		
	}
}