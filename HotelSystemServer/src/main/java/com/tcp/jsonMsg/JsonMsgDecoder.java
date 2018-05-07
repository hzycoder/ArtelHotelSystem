package com.tcp.jsonMsg;

import java.io.EOFException;
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
		System.out.println(in);
		byte[] byteArray = Util.readBuffer(in);
		Object obj;
		try {
			obj = Util.bytes2Object(byteArray);
			out.add(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
