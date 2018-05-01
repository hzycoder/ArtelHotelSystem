package com.tcp.netty;

import com.alibaba.fastjson.JSONObject;
import com.tcp.ChannelSession;
import com.tcp.FrameStruct.FrameStruct;
import com.tcp.jsonMsg.JsonMsg;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		System.out.println("※※※※※※※※※※※客户端与服务端通道激活" + id);
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
		System.out.println("------接收到来自服务器的数据------");
		JsonMsg jsonMsg = (JsonMsg) msg;
		System.out.println("[Client]:" + jsonMsg.getJsonContent());
		
		// System.out.println(((FrameStruct) msg).toString());
		// if (msg instanceof FrameStruct) {
		// FrameStruct fs = (FrameStruct) msg;
		// System.out.println("[Client]:" + (byte[])fs.getContent());
		// } else {
		// System.out.println("[Client]:not instanceof FrameStruct");
		// }
		// count++;
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel 通道 Read 读取 Complete 完成");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}
