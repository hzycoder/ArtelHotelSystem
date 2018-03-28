package com.tcp;


import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class FrameDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() >= Constants.HEAD_LENGTH) {
			
			int beginReader;
//			while (true) {
////				beginReadr = in.readerIndex();
////				in.markReaderIndex();
////				if
//				
//			}
		}
	}

}
