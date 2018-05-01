package com.tcp.netty;

import com.tcp.ChannelSession;
import com.tcp.MsgQueue;
import com.tcp.jsonMsg.JsonMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	public static MsgQueue<JsonMsg> queueu = new MsgQueue<JsonMsg>();
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
		System.out.println("[Client]:" + jsonMsg.getJsonObject().toJSONString());
		queueu.push(jsonMsg);//把消息放进消息队列
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
		System.out.println("----------接收数据完成----------");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}
