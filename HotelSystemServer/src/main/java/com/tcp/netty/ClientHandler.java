package com.tcp.netty;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.tcp.ChannelSession;
import com.tcp.frameStruct.ConstantValue;
import com.tcp.MsgQueue;
import com.tcp.frameStruct.FrameStruct;
import com.tcp.newStruct.JsonStruct;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	public static MsgQueue<JsonStruct> queue = new MsgQueue<JsonStruct>();
	public static MsgQueue<JsonStruct> bindingQueue = new MsgQueue<JsonStruct>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		System.out.println("※※※※※※※※※※※客户端与服务端通道激活" + id);
		ChannelSession.addChannelSession(id, ctx.channel());
		
		//web token 验证包
		StringBuffer sb = new StringBuffer();
		sb.append("00000000000000");
		sb.append("03bc156ac796828d0d08625f86f6dc55");
		byte[] tokenByte = sb.toString().getBytes();
		
		FrameStruct token  = new FrameStruct(tokenByte.length, tokenByte);
		ChannelFuture channelFuture = ctx.writeAndFlush(token);
		channelFuture.await();
		System.out.println("验证包是否发送成功："+channelFuture.isSuccess());
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(8);// 先定义8个线程空间
		service.scheduleAtFixedRate(new DelThread(), 0, 8, TimeUnit.SECONDS);
		// CrawlerTest()是一个实现Runnable接口的类，会自动运行里面的run()方法，0的意思就是启动等待时间，这里就是直接运行，
		// 10是10分钟，要是想小时，就把TimeUnit.MINUTES换成 TimeUnit.HOURS
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

//		// 测试解决半包
//		JsonStruct jsonStruct = (JsonStruct) msg;
		
		//换FrameStruct
		FrameStruct frameStruct = (FrameStruct)msg;
		String msg1 = new String(frameStruct.getContent());
		System.out.println("channelRead接收的数据"+msg1);
		
		JsonStruct jsonStruct = new JsonStruct();
		jsonStruct.setContent(JSONObject.parseObject(msg1));
		
		queue.push(jsonStruct);// 把消息放进消息队列
		long l = new Date().getTime();
		if ((int) l % 3 == 0) {
			System.out.println("当前消息队列数据量：" + queue.getStorage().size());
		}
		// count++;
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
		System.out.println("~~~~~~~~heart~~~~~~~~: "+sdf.format(new Date()));
	    if (evt instanceof IdleStateEvent){
	        IdleStateEvent event = (IdleStateEvent)evt;
	        if (event.state() == IdleState.WRITER_IDLE){
	        	byte[] heart = {};
	        	FrameStruct heartFrame = new FrameStruct(heart.length,heart);
	            ctx.writeAndFlush(heartFrame);
	        }
	    }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}

/**
 * @author huangzhenyang 删除线程
 */
class DelThread implements Runnable {

	@Override
	public void run() {
		System.out.println("--------执行删除线程--------");
		ConcurrentLinkedQueue<JsonStruct> queue = ClientHandler.queue.getStorage();
		while (true) {
			long time = new Date().getTime();
			JsonStruct jsonStruct = queue.peek();
			if (jsonStruct != null) {
				JSONObject json = jsonStruct.getContent();
				// System.out.println("获取头部："+json.toJSONString());
				long l = new Date().getTime();
//				System.out.println("当前时间：" + time + "   消息发送时间：" + json.getLong("发送时间"));
				if (json.getLong("TIME") == null) {
					queue.poll();
					System.out.println("消息超时，1条消息被移除队列，当前队列数据量为：" + queue.size());
				}else if (time - json.getLong("TIME") > 1000 * 10) {
					queue.poll();
					System.out.println("消息超时，1条消息被移除队列，当前队列数据量为：" + queue.size());
					continue;
				}
			}
		}
		// Iterator iter = queue.iterator();
		// while (iter.hasNext()) {
		// JsonStruct jsonStruct = (JsonStruct) iter.next();
		// JSONObject json = jsonStruct.getContent();
		// String jsonContent = json.toJSONString();
		// if (time - json.getLong("sendTime") > 1000*60) {
		//// System.out.println("这个数据包已超时，将被删除：" + jsonContent);
		// continue;
		// }
		// }
	}

}


class HeartThread implements Runnable {
	Channel channel = null;
	

	@Override
	public void run() {
		while (true) {
		}
	}

}