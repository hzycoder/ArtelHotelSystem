package com.tcp.netty;

import com.tcp.ChannelSession;
import com.tcp.MsgQueue;
import com.tcp.newStruct.JsonStruct;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	public static MsgQueue<JsonStruct> queueu = new MsgQueue<JsonStruct>();

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
		count++;
		// JsonMsg jsonMsg = (JsonMsg) msg;
		// System.out.println("[Client]"+count+":" + jsonMsg.getJsonContent());

		// 测试解决半包
		JsonStruct jsonStruct = (JsonStruct) msg;
//		System.out.println("[Client]" + count + ":" + jsonStruct.getContent());
		queueu.push(jsonStruct);// 把消息放进消息队列

		// count++;
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}
