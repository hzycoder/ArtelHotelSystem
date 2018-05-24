package com.tcp.netty;

import com.tcp.newStruct.JsonDecoder;
import com.tcp.newStruct.JsonEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientBootstrap {
	private static void connect(String ip, int port) {
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
				// pipeline.addLast(new SyncFrameCoder());
				// pipeline.addLast(new SyncFrameDecoder());
				// pipeline.addLast(new JsonMsgEncoder());
				// pipeline.addLast(new JsonMsgDecoder());
				
				//自定义json编解码
				pipeline.addLast(new JsonDecoder());
				pipeline.addLast(new JsonEncoder());
				// 添加handler
				pipeline.addLast(new ClientHandler());
			}

		});

		// 让客户端和服务端连接
		ChannelFuture channelFuture = bootstrap.connect(ip, port);

		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("※※※※※※※※※※※Connect susccess");
				} else {
					System.out.println("※※※※※※※※※※※Connect failure");
				}

			}
		});

	}

	public static void connectServer(String ip, int port) {
		System.out.println("开始尝试连接");
		connect(ip, port);
	}
}
