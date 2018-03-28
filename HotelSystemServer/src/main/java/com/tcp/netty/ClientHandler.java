package com.tcp.netty;


import com.tcp.ChannelSession;
import com.tcp.FrameStruct;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		System.out.println("※※※※※※※※※※※客户端与服务端通道激活"+id);
//		ctx.writeAndFlush("i am Client\r\n");
//		ctx.writeAndFlush("i am Client\r\n");
//		ctx.writeAndFlush("我是客户端！");
		ChannelSession.addChannelSession(id, ctx.channel());
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("通道失效");
		super.channelInactive(ctx);
	}

	int count = 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("※※※※※※※※※※※开始读取数据");
//		System.out.println("※※※※※※※※※※※从服务端接收的数据:" + (String)msg + ";count:" + count);
		if (msg instanceof FrameStruct) {
			FrameStruct fs = (FrameStruct) msg;
			System.out.println("[Client]:"+fs.getContent());
		}else {
			System.out.println("[Client]:not instanceof FrameStruct");
		}
		count++;
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause.getMessage());
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}
