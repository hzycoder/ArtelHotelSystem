package com.tcp.netty;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.tcp.ChannelSession;
import com.tcp.MsgQueue;
import com.tcp.newStruct.JsonStruct;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	public static MsgQueue<JsonStruct> queue = new MsgQueue<JsonStruct>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		System.out.println("※※※※※※※※※※※客户端与服务端通道激活" + id);
		ChannelSession.addChannelSession(id, ctx.channel());
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

		// 测试解决半包
		JsonStruct jsonStruct = (JsonStruct) msg;
		// System.out.println("[Client]" + count + ":" +
		// jsonStruct.getContent());
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
				if (json.getLong("发送时间") == null) {
					queue.poll();
					System.out.println("消息超时，1条消息被移除队列，当前队列数据量为：" + queue.size());
				}else if (time - json.getLong("发送时间") > 1000 * 10) {
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
