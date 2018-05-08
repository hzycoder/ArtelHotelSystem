package com.tcp.newStruct;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class JsonDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {// 整个数据包小于头部长度
			return;
		}
		int beginIndex = in.readerIndex();// 获取bytebuf的readerIndex
		int length = in.readInt();// 读取前四个四节并且移动readerIndex 4的单位
		if ((in.readableBytes() + 1) < length) {// 数据体长度小于头部表明的长度
			in.readerIndex(beginIndex);
			return;
		}
		in.readerIndex(beginIndex + 4);// 设置readerIndex跨过头部
		byte[] data = new byte[length];
		in.readBytes(data); // 设置数据体内容并移动readerIndex跨过内容的长度 以便下一个包的解码
		JsonStruct jsonStruct = new JsonStruct(length, JSONObject.parseObject(new String(data)));
		out.add(jsonStruct);
	}
}
