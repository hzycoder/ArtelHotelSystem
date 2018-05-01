package com.tcp.jsonMsg;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tcp.Util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author huangzhenyang
 * 解码器
 */
public class JsonMsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] byteArray = Util.readBuffer(in);
		Object obj = Util.bytes2Object(byteArray);
		out.add(obj);
	}

}
