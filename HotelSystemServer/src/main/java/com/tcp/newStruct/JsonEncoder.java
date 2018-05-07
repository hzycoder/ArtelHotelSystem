package com.tcp.newStruct;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JsonEncoder extends MessageToByteEncoder<JsonStruct> {

	@Override
	protected void encode(ChannelHandlerContext ctx, JsonStruct msg, ByteBuf out) throws Exception {
		out.writeInt(msg.getHeadLength());
		out.writeBytes(msg.getContent().toJSONString().getBytes());
	}

}
