package com.tcp.jsonMsg;

import com.alibaba.fastjson.JSONObject;
import com.tcp.Util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author huangzhenyang
 * 编码器
 */
public class JsonMsgEncoder extends MessageToByteEncoder<JsonMsg>{

	@Override
	protected void encode(ChannelHandlerContext ctx, JsonMsg msg, ByteBuf out) throws Exception {
		byte[] byteArray = Util.object2Bytes(msg);
		out.writeBytes(byteArray);
	}

}
