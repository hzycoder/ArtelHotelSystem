package com.tcp.FrameStruct;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SyncFrameCoder extends MessageToByteEncoder<FrameStruct> {
	@Override
	protected void encode(ChannelHandlerContext tcx, FrameStruct msg, ByteBuf out) throws Exception {
		
		out.writeInt(msg.getHeadData());
		out.writeShort(msg.getContentLength());
		out.writeBytes(msg.getContent());
		
	}
}
